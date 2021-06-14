package sisdentalfx.db.entidades;

import java.util.ArrayList;

public class Consulta {

    public class itensProc {

        private int qtd;
        private Procedimentos procedimentos;

        public itensProc() {
            this(0, null);
        }

        public itensProc(int qtd, Procedimentos procedimentos) {
            this.qtd = qtd;
            this.procedimentos = procedimentos;
        }

        public int getQtd() {
            return qtd;
        }

        public void setQtd(int qtd) {
            this.qtd = qtd;
        }

        public Procedimentos getProcedimentos() {
            return procedimentos;
        }

        public void setProcedimentos(Procedimentos procedimentos) {
            this.procedimentos = procedimentos;
        }
    }

    public class itensMat {

        private int qtd;
        private Material material;

        public itensMat() {
            this(0, null);
        }

        public itensMat(int qtd, Material material) {
            this.qtd = qtd;
            this.material = material;
        }

        public int getQtd() {
            return qtd;
        }

        public void setQtd(int qtd) {
            this.qtd = qtd;
        }

        public Material getMaterial() {
            return material;
        }

        public void setMaterial(Material material) {
            this.material = material;
        }
    }

    private String obs;
    private boolean status;
    private ArrayList<itensMat> materiais;
    private ArrayList<itensProc> procedimentos;

    //perguntar se precisa de um construtor sem material ou sem procedimento !!! Pode ter uma consulta com materail e sem procedeimente e vice versa ? acho que não !
    public Consulta() {
        this("", false);
    }

    public Consulta(String obs, boolean status) {
        this.obs = obs;
        this.status = status;
        this.materiais = new ArrayList();
        this.procedimentos = new ArrayList();
    }
    
    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public ArrayList<itensMat> getMateriais() {
        return materiais;
    }

    public void addMaterial(Material material, int quant) {
        this.materiais.add(new itensMat(quant, material));
    }
    
    public ArrayList<itensProc> getProcedimentos() {
        return procedimentos;
    }
    
    public void addProcedimentos(Procedimentos proc, int quant) {
        this.procedimentos.add(new itensProc(quant, proc));
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
