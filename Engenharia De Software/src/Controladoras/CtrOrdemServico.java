package Controladoras;

import Entidades.Boleto;
import Entidades.Cartao;
import Entidades.Cliente;
import Entidades.Dinheiro;
import Entidades.Funcionario;
import Entidades.NovaEntidades.ItemOrdemProduto;
import Entidades.NovaEntidades.ItemOrdemServico;
import Entidades.NovaEntidades.Orcamento;
import Entidades.NovaEntidades.OrdemServico;
import Interface.Controladora;
import Interface.Strategy;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CtrOrdemServico implements Controladora
{

    private static CtrOrdemServico con;

   

    private CtrOrdemServico()
    {
    }

    public static CtrOrdemServico instancia()
    {
        if (con == null)
        {
            con = new CtrOrdemServico();
        }
        return con;
    }
    public static boolean Adiciona(int cli_cod, Object funcionario, String parcelas, double total, LocalDate dtos, LocalDate dtfechamento, Object orcamento, JFXRadioButton rbBoleto, JFXRadioButton rbCartao, JFXRadioButton rbDinheiro, ObservableList<Object> listaItens)
    {
        ArrayList<ItemOrdemServico> servicos = new ArrayList();
        ArrayList<ItemOrdemProduto> produtos = new ArrayList();
        
        for (int i = 0; i < listaItens.size(); i++) 
        {
            if(listaItens.get(i) instanceof ItemOrdemProduto)
                produtos.add((ItemOrdemProduto)listaItens.get(i));
            else if(listaItens.get(i) instanceof ItemOrdemServico)
                servicos.add((ItemOrdemServico)listaItens.get(i));
        }
        
        Strategy forma = (rbBoleto.isSelected())? new Boleto(5.0) 
                : ((rbCartao.isSelected())? 
                    new Cartao(2.0)
                    : new Dinheiro(10.0));
        OrdemServico os = new OrdemServico((Orcamento) orcamento, new Cliente(cli_cod), (Funcionario) funcionario, Date.valueOf(dtos), Date.valueOf(dtfechamento), Integer.parseInt(parcelas), total, forma);
        
        return os.insert();
    }

    public static void AtulizaPArcelas(JFXComboBox<String> cbparcelas, Label lblValorParcelas, Object orcamento)
    {
        Orcamento oc = (Orcamento) orcamento;
        if (cbparcelas.getSelectionModel().getSelectedItem() != null)
        {
            Double totd = oc.getTotal() / Integer.parseInt(cbparcelas.getSelectionModel().getSelectedItem());
            lblValorParcelas.setText(totd.toString().substring(0, totd.toString().indexOf(".") + 2));
        }
    }

    public static void AtualizaTotal(Label lblTotalOS, Object orcamento)
    {
        Orcamento oc = (Orcamento) orcamento;
        lblTotalOS.setText(oc.getTotal().toString());
    }

    public static void AtualizaClienteANDFuncionario(JFXTextField txcodigocliente, JFXTextField txcpf, JFXTextField txnomecliente, JFXTextField txrgcliente, JFXComboBox<Object> cbfuncionario, Object orcamento)
    {
        Orcamento o = (Orcamento) orcamento;
        txcpf.setText(o.getCliente().getCpf());
        txcodigocliente.setText(o.getCliente().getCodigo().toString());
        txnomecliente.setText(o.getCliente().getNome());
        txrgcliente.setText(o.getCliente().getRg());
        cbfuncionario.getItems().clear();
        cbfuncionario.getItems().add(o.getUsuarioid());
        cbfuncionario.getSelectionModel().select(o.getUsuarioid());
    }

    public static void GetInfoTabela(TableView<Object> tabela, String string)
    {
        ObservableList<Object> o = FXCollections.observableArrayList(OrdemServico.getAll(""));
        tabela.setItems(o);
    }

    @Override
    public Boolean Salvar()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Alterar()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean Excluir()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
