
package Controladoras;

import Entidades.Cliente;
import Entidades.Funcionário;
import Entidades.A.ItemOrcamentoProduto;
import Entidades.A.ItemOrcamentoServico;
import Entidades.A.Orcamento;
import Entidades.A.Servico;
import Entidades.Funcionario;
import Entidades.Produto;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CtrOrcamento
{

    private static CtrOrcamento con;

    private CtrOrcamento()
    {
    }

    public static CtrOrcamento instancia()
    {
        if (con == null)
        {
            con = new CtrOrcamento();
        }
        return con;
    }

    public static void PreencheCampos(Object orcamento, JFXTextField txcodigo, JFXTextField txcodigocliente, JFXTextField txcpf, JFXTextField txfuncionario, JFXTextField txnomecliente, JFXTextArea txobs, JFXTextField txrgcliente, TableView<Object> cbprodutos, TableView<Object> cbservicos, JFXDatePicker dtorcamento, JFXDatePicker dtvalidade)
    {
        Orcamento o = (Orcamento) orcamento;
        if (o != null)
        {
            try
            {
                txcodigo.setText(Integer.toString(o.getCodigo()));
                txcodigocliente.setText(Integer.toString(o.getCliente().getCodigo()));
                txcpf.setText(o.getCliente().getCpf());
                txfuncionario.setText(o.getUsuarioid().getNome());
                txobs.setText(o.getObsformapagamento());
                txrgcliente.setText(o.getCliente().getRg());
                dtorcamento.setValue(o.getDtorcamento().toLocalDate());
                dtvalidade.setValue(o.getDtvalidade().toLocalDate());
                cbprodutos.setItems(FXCollections.observableArrayList(o.getProdutosOrcamento()));
                cbservicos.setItems(FXCollections.observableArrayList(o.getServicosOrcamento()));
            } catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static Boolean Alterar(int c_orcamento, int codigo_cliente, LocalDate dtorcamento, LocalDate dtvalidade, Double total, String obs, int id_funcionario, ObservableList<Object> produtos, ObservableList<Object> servicos)
    {
        boolean flag = false;
        ArrayList<ItemOrcamentoProduto> po = new ArrayList<>();
        ArrayList<ItemOrcamentoServico> so = new ArrayList<>();
        Orcamento o = null;
        try
        {
            for (int i = 0; i < produtos.size(); i++)
            {
                po.add((ItemOrcamentoProduto) produtos.get(i));
            }
            for (int i = 0; i < servicos.size(); i++)
            {
                so.add((ItemOrcamentoServico) servicos.get(i));
            }
            Cliente c = new Cliente(codigo_cliente);
            Funcionario f = new Funcionario();
            f.setCodigo(id_funcionario);
            o = new Orcamento(c_orcamento, Date.valueOf(dtorcamento),Date.valueOf(dtvalidade),total,obs,f,c, po, so);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        if (o != null)
        {
            flag = o.update();
        }
        return flag;
    }

    public static boolean Adicionar(int codigo_cliente, LocalDate dtorcamento, LocalDate dtvalidade, Double total, String obs, int id_funcionario, ObservableList<Object> produtos, ObservableList<Object> servicos)
    {
        boolean flag = false;
        Orcamento o = null;
        ArrayList<ItemOrcamentoProduto> po = new ArrayList<>();
        ArrayList<ItemOrcamentoServico> so = new ArrayList<>();
        
        try
        {
            for (int i = 0; i < produtos.size(); i++)
            {
                po.add((ItemOrcamentoProduto) produtos.get(i));
            }
            for (int i = 0; i < servicos.size(); i++)
            {
                so.add((ItemOrcamentoServico) servicos.get(i));
            }
            Cliente c = new Cliente(codigo_cliente);
            Funcionario f = new Funcionario(id_funcionario);
            o = new Orcamento(Date.valueOf(dtorcamento),
                    Date.valueOf(dtvalidade),
                    total,
                    obs,
                    f,
                    c, po, so);
        } catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }

        if (o != null)
        {
            flag = o.insert();
        }
        return flag;
    }

    public static void alteraProdutoSelecionado(TableView<Object> cbprodutos, JFXTextField txquantidadeservico, JFXTextField preco, Label erro)
    {
        if (txquantidadeservico.getText().isEmpty())
        {
            erro.setVisible(true);
        } else if (cbprodutos.getSelectionModel().getSelectedItem() != null)
        {
            int q = Integer.parseInt(txquantidadeservico.getText());
            Produto p = ((ItemOrcamentoProduto) cbprodutos.getSelectionModel().getSelectedItem()).getProduto();
            ((ItemOrcamentoProduto) cbprodutos.getSelectionModel().getSelectedItem()).setQtd(q);
            ((ItemOrcamentoProduto) cbprodutos.getSelectionModel().getSelectedItem()).setValor(p.getPreco() * q);

            txquantidadeservico.setText("");
            preco.setText("");
        }

    }

    public static void alteraServicoSelecionado(TableView<Object> cbservicos, JFXTextField txquantidadeservico, JFXTextField preco, Label erro)
    {
        if (txquantidadeservico.getText().isEmpty() || preco.getText().isEmpty())
        {
            erro.setVisible(true);
        } else if (cbservicos.getSelectionModel().getSelectedItem() != null)
        {
            ItemOrcamentoServico is = (ItemOrcamentoServico) cbservicos.getSelectionModel().getSelectedItem();
            int q;
            Double d;
            q = Integer.parseInt(txquantidadeservico.getText());
            d = Double.parseDouble(preco.getText().replace(",", "."));

            Servico s = ((ItemOrcamentoServico) cbservicos.getSelectionModel().getSelectedItem()).getServico();
            is.setQtd(q);
            is.setValor(d * q);

            cbservicos.getItems().set(cbservicos.getSelectionModel().getSelectedIndex(), is);
            txquantidadeservico.setText("");
            preco.setText("");
        }
    }

    public static boolean apagarProdutoDoOrcamento(TableView<Object> t)
    {
        Object selectedItem = t.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
        {
            return false;
        } else
        {
            ItemOrcamentoProduto p = (ItemOrcamentoProduto) selectedItem;
            if (p.getCodigo_orcamento() == null)//ainda não inserido
            {
                t.getItems().remove(selectedItem);
                return true;
            } else
            {
                return p.remove();
            }
        }
    }

    public static boolean apagarServicoDoOrcamento(TableView<Object> t)
    {
        Object selectedItem = t.getSelectionModel().getSelectedItem();
        if (selectedItem == null)
        {
            return false;
        } else
        {
            ItemOrcamentoServico s = (ItemOrcamentoServico) selectedItem;
            if (s.getCodigo_orcamento() == null)
            {
                t.getItems().remove(selectedItem);
                return true;
            } else
            {
                return s.remove();
            }
        }
    }

    public static void PreencheCamposPS(JFXTextField txquantidade, JFXTextField txvalor, Object o)
    {
        if (o != null)
        {
            if (o instanceof ItemOrcamentoProduto)
            {
                ItemOrcamentoProduto p = (ItemOrcamentoProduto) o;
                txquantidade.setText(Integer.toString(p.getQtd()));
                String aux = p.getValor().toString().substring(p.getValor().toString().length() - 2, p.getValor().toString().length() - 1).equals(".") ? "0" : "";
                txvalor.setText(p.getValor().toString() + aux);
            } else if (o instanceof ItemOrcamentoServico)
            {
                ItemOrcamentoServico s = (ItemOrcamentoServico) o;
                txquantidade.setText(Integer.toString(s.getQtd()));
                String aux = s.getValor().toString().substring(s.getValor().toString().length() - 2, s.getValor().toString().length() - 1).equals(".") ? "0" : "";
                txvalor.setText(s.getValor().toString() + aux);
            }
        }
    }

    public static void AtualizaTot(Label lbltotal, TableView<Object> cbprodutos, TableView<Object> cbservicos)
    {
        Double c = 0.0;
        for (int i = 0; i < cbprodutos.getItems().size(); i++)
        {
            c += ((ItemOrcamentoProduto) cbprodutos.getItems().get(i)).getValor();
        }
        for (int i = 0; i < cbservicos.getItems().size(); i++)
        {
            c += ((ItemOrcamentoServico) cbservicos.getItems().get(i)).getValor();
        }
        lbltotal.setText(Double.toString(c));
    }

    public static boolean ApagarOrcamento(int parseInt)
    {
        return new Orcamento(parseInt).delete();
    }

    public static ObservableList<Object> getInfoTabela(String filtro, String op)
    {
        ArrayList<Object> a = new Orcamento().getAvancado(filtro, op);
        ObservableList<Object> ob = FXCollections.observableArrayList(a);
        return ob;
    }

    public static ObservableList<Object> getInfoTabelaAvancado(String cliente_cpf, String funcionario_nome, BooleanProperty considerardatas, Date inicio, Date fim)
    {
        ArrayList<Object> a = new Orcamento().getAvancado(cliente_cpf, funcionario_nome, considerardatas, inicio, fim);
        ObservableList<Object> ob = FXCollections.observableArrayList(a);
        return ob;
    }

    public static Object getItemOrcamentoServico(Object io, Double p, Integer q)
    {
        return (io != null && io instanceof Servico) ? new ItemOrcamentoServico((Servico) io, 0.0, 0) : null;
    }

    public static Object getItemOrcamentoProduto(Object io)
    {
        return (io != null && io instanceof Produto) ? new ItemOrcamentoProduto((Produto) io) : null;
    }

}
