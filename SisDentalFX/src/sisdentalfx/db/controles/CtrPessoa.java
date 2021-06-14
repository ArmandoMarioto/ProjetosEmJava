package sisdentalfx.db.controles;

import java.sql.ResultSet;
import java.util.ArrayList;
import sisdentalfx.db.entidades.Dentista;
import sisdentalfx.db.entidades.Paciente;
import sisdentalfx.db.entidades.Pessoa;
import sisdentalfx.db.entidades.Usuario;
import sisdentalfx.db.util.Banco;

public class CtrPessoa {

    private String sql = "";

    public boolean salvar(Pessoa p) {
        sql = "insert into ";

        if (p instanceof Usuario) {
            sql += "usuario(usu_nome, usu_nivel, usu_senha) values('$1', $2, '$3')";

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", " " + ((Usuario) p).getNivel());
            sql = sql.replace("$3", ((Usuario) p).getSenha());
        } else if (p instanceof Dentista) {
            sql += "dentista(den_nome, den_email, den_cro, den_fone) values('$1', '$2', '$3', '$4')";

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", ((Dentista) p).getEmail());
            sql = sql.replace("$3", ((Dentista) p).getCRO());
            sql = sql.replace("$4", ((Dentista) p).getTelefone());
        } else if (p instanceof Paciente) {
            sql += "paciente(pac_cpf, pac_nome, pac_bairro, pac_tipo_log, pac_logradouro, pac_numero, pac_fone, pac_email, pac_historico, cid_cod, pac_cep)"
                    + " values('$1', '$2', '$3', '$4', '$5', '$6', '$7', '$8', '$9','$b', '$c')";

            sql = sql.replace("$1", ((Paciente) p).getCPF());
            sql = sql.replace("$2", ((Paciente) p).getNome());
            sql = sql.replace("$3", ((Paciente) p).getBairro());
            sql = sql.replace("$4", ((Paciente) p).getTipo());
            sql = sql.replace("$5", ((Paciente) p).getRua());
            sql = sql.replace("$6", ((Paciente) p).getNumero());
            sql = sql.replace("$7", ((Paciente) p).getTelefone());
            sql = sql.replace("$8", ((Paciente) p).getEmail());
            sql = sql.replace("$9", ((Paciente) p).getHisClinico());
            sql = sql.replace("$b", "" + ((Paciente) p).getCidade().getCodigo());
            sql = sql.replace("$c", ((Paciente) p).getCEP());
        }

        return Banco.con.manipular(sql);
    }

    public boolean alterar(Pessoa p) {
        sql = "update ";

        if (p instanceof Usuario) {
            sql += "usuario set usu_nome = '$1', usu_nivel = $2, usu_senha = '$3' where usu_codigo = " + ((Usuario) p).getCodigo();

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", " " + ((Usuario) p).getNivel());
            sql = sql.replace("$3", ((Usuario) p).getSenha());
        } else if (p instanceof Dentista) {
            sql += "dentista set den_nome = '$1', den_email = '$2',den_cro = '$3', den_fone = '$4' where den_codigo = " + ((Dentista) p).getCodigo();

            sql = sql.replace("$1", p.getNome());
            sql = sql.replace("$2", ((Dentista) p).getEmail());
            sql = sql.replace("$3", ((Dentista) p).getCRO());
            sql = sql.replace("$4", ((Dentista) p).getTelefone());
        } else if (p instanceof Paciente) {
            sql += "paciente set pac_cpf = '$1', pac_nome = '$2', pac_bairro = '$3', pac_tipo_log = '$4', pac_logradouro = '$5', pac_numero = '$6', "
                    + "pac_fone = '$7', pac_email = '$8', pac_historico = '$9', cid_cod = '$b', pac_cep = '$c'"
                    + " where pac_codigo = " + ((Paciente) p).getCodigo();
            sql = sql.replace("$1", ((Paciente) p).getCPF());
            sql = sql.replace("$2", ((Paciente) p).getNome());
            sql = sql.replace("$3", ((Paciente) p).getBairro());
            sql = sql.replace("$4", ((Paciente) p).getTipo());
            sql = sql.replace("$5", ((Paciente) p).getRua());
            sql = sql.replace("$6", ((Paciente) p).getNumero());
            sql = sql.replace("$7", ((Paciente) p).getTelefone());
            sql = sql.replace("$8", ((Paciente) p).getEmail());
            sql = sql.replace("$9", ((Paciente) p).getHisClinico());
            sql = sql.replace("$b", "" + ((Paciente) p).getCidade().getCodigo());
            sql = sql.replace("$c", ((Paciente) p).getCEP());
        }

        return Banco.con.manipular(sql);
    }

    public boolean apagar(Pessoa p) {
        sql = "delete from ";

        if (p instanceof Usuario) {
            sql += "usuario where usu_codigo = " + ((Usuario) p).getCodigo();
        } else if (p instanceof Dentista) {
            sql += "dentista where den_codigo = " + ((Dentista) p).getCodigo();
        } else if (p instanceof Paciente) {
            sql += "paciente where pac_codigo = " + ((Paciente) p).getCodigo();
        }

        return Banco.con.manipular(sql);
    }

    public Pessoa getPessoa(int codigo, Pessoa p) {
        Pessoa rsP = null;
        sql = "select * from ";

        if (p instanceof Usuario) {
            sql += "usuario where usu_codigo = " + codigo;
            ResultSet rs = Banco.con.consultar(sql);

            try {
                if (rs.next()) {
                    rsP = new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), rs.getInt("usu_nivel"), rs.getString("usu_senha"));
                }
            } catch (Exception e) {
                //
            }
        } else if (p instanceof Dentista) {
            CtrAgenda ctrA = new CtrAgenda(); // Fazer e alterar dentro do if(rs.next())

            sql += "dentista where den_codigo = " + codigo;
            ResultSet rs = Banco.con.consultar(sql);

            try {
                if (rs.next()) {
                    rsP = new Dentista(rs.getInt("den_codigo"), rs.getString("den_nome"), rs.getString("den_cro"), rs.getString("den_telefone"), rs.getString("den_email"), null);
                }
            } catch (Exception e) {
                //
            }
        } else if (p instanceof Paciente) {
            CtrCidade ctrC = new CtrCidade();

            sql += "paciente where pac_codigo = " + codigo;
            ResultSet rs = Banco.con.consultar(sql);

            try {
                if (rs.next()) {
                    rsP = new Paciente(rs.getInt("pac_codigo"), rs.getString("pac_nome"), rs.getString("pac_cpf"), rs.getString("pac_fone"), rs.getString("pac_email"),
                            rs.getString("pac_historico"), rs.getString("pac_cep"), rs.getString("pac_bairro"), rs.getString("pac_logradouro"), rs.getString("pac_numero"),
                            /*rs.getBlob("pac_foto")*/ null, ctrC.getCidade(rs.getInt("cid_cod")), rs.getString("pac_tipo_log"));
                }
            } catch (Exception e) {
                System.out.println("ERRO AO TRAZER O MALDITO PACIENTE: " + e.getMessage());
            }
        }
        
        return rsP;
    }

    public Pessoa getPessoa(Pessoa p) {
        Pessoa rsP = null;
        sql = "select * from ";

        if (p instanceof Usuario) {
            sql += "usuario where usu_codigo = " + ((Usuario) p).getCodigo();
            ResultSet rs = Banco.con.consultar(sql);

            try {
                if (rs.next()) {
                    rsP = new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), rs.getInt("usu_nivel"), rs.getString("usu_senha"));
                }
            } catch (Exception e) {
                //
            }
        } else if (p instanceof Dentista) {
            CtrAgenda ctrA = new CtrAgenda(); // Fazer e alterar dentro do if(rs.next())

            sql += "dentista where den_codigo = " + ((Dentista) p).getCodigo();
            ResultSet rs = Banco.con.consultar(sql);

            try {
                if (rs.next()) {
                    rsP = new Dentista(rs.getInt("den_codigo"), rs.getString("den_nome"), rs.getString("den_cro"), rs.getString("den_telefone"), rs.getString("den_email"), null);
                }
            } catch (Exception e) {
                //
            }
        } else if (p instanceof Paciente) {
            CtrCidade ctrC = new CtrCidade();

            sql += "paciente where pac_codigo = " + ((Paciente) p).getCodigo();
            ResultSet rs = Banco.con.consultar(sql);

            try {
                if (rs.next()) {
                    rsP = new Paciente(rs.getInt("pac_codigo"), rs.getString("pac_nome"), rs.getString("pac_cpf"), rs.getString("pac_fone"), rs.getString("pac_email"),
                            rs.getString("pac_historico"), rs.getString("pac_cep"), rs.getString("pac_bairro"), rs.getString("pac_logradouro"), rs.getString("pac_numero"),
                            rs.getBlob("pac_foto"), ctrC.getCidade(rs.getInt("cid_cod")), rs.getString("pac_tipo_log"));
                }
            } catch (Exception e) {
                //
            }
        }
        return rsP;
    }

    public ArrayList<Pessoa> getPessoa(String filtro, Pessoa p) {
        ArrayList<Pessoa> pes = new ArrayList();
        ResultSet rs;

        sql = "select * from ";

        if (p instanceof Usuario) {
            sql += "usuario ";

            if (!filtro.isEmpty()) {
                sql += "where " + filtro;
            }

            rs = Banco.con.consultar(sql);

            try {
                while (rs.next()) {
                    pes.add(new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), ((char) rs.getByte("usu_nivel")), rs.getString("usu_senha")));
                }
            } catch (Exception e) {
                //erro
            }
        } else if (p instanceof Dentista) {
            sql += "dentista ";

            if (!filtro.isEmpty()) {
                sql += "where " + filtro;
            }
            rs = Banco.con.consultar(sql);

            try {
                while (rs.next()) {
                    pes.add(new Dentista(rs.getInt("den_codigo"), rs.getString("den_nome"), rs.getString("den_cro"), rs.getString("den_fone"), rs.getString("den_email"), null));
                }
            } catch (Exception e) {
                //erro
            }
        } else if (p instanceof Paciente) {
            CtrCidade ctrC = new CtrCidade();

            sql += "paciente";

            if (!filtro.isEmpty()) {
                sql += " where " + filtro;
            }

            rs = Banco.con.consultar(sql);

            try {
                while (rs.next()) {
                    pes.add(new Paciente(rs.getInt("pac_codigo"), rs.getString("pac_nome"), rs.getString("pac_cpf"), rs.getString("pac_fone"),
                            rs.getString("pac_email"), rs.getString("pac_historico"), rs.getString("pac_cep"),
                            rs.getString("pac_bairro"), rs.getString("pac_logradouro"), rs.getString("pac_numero"),
                            null, ctrC.getCidade(rs.getInt("cid_cod")), rs.getString("pac_tipo_log")));
                }
            } catch (Exception e) {
                //erro
            }
        }
        return pes;
    }

    public Pessoa getPessoa(String nome, String senha) {
        Pessoa u = null;
        sql = "select * from usuario where usu_nome = '" + nome + "' and usu_senha = '" + senha + "'";

        ResultSet rs = Banco.con.consultar(sql);

        try {
            if (rs.next()) {
                u = new Usuario(rs.getInt("usu_codigo"), rs.getString("usu_nome"), rs.getInt("usu_nivel"), rs.getString("usu_senha"));
            }
        } catch (Exception e) {
            //erro
        }
        return u;
    }
}
