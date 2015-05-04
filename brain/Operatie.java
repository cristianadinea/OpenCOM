package brain;

public class Operatie {

    private String stang = "";
    private String drept = "";
    private String operatie = "";
    private String rezultat = "";   

    void addStang(String s) {
        this.stang = s;
    }

    void addDrept(String s) {
        this.drept = s;
    }

    String getStang() {
        return this.stang;
    }

    String getDrept() {
        return this.drept;
    }
    
    String getRezultat() {
        return this.rezultat;
    }
    
    void setRezultat(String s) {
        this.rezultat = s;
    }
    
    String getOperatie() {
        return this.operatie;
    }
    
    void setOperatie(String s) {
        this.operatie = s;
    }
    
	public int operate() {
		if (this.operatie.equals("+")) {
			return ExpressionInterpreter.pICalc.add(
					Integer.valueOf(this.stang), Integer.valueOf(this.drept));
		} else if (this.operatie.equals("-")) {
			return ExpressionInterpreter.pICalc.subtract(
					Integer.valueOf(this.stang), Integer.valueOf(this.drept));
		} else if (this.operatie.equals("*")) {
			return ExpressionInterpreter.pICalc.multiply(
					Integer.valueOf(this.stang), Integer.valueOf(this.drept));
		} else if (this.operatie.equals("/")) {
			return ExpressionInterpreter.pICalc.divide(
					Integer.valueOf(this.stang), Integer.valueOf(this.drept));
		} else if (this.operatie.equals("++")) {
			return ExpressionInterpreter.pICalc.inc(Integer.valueOf(this.stang));
		} else if (this.operatie.equals("--")) {
			return ExpressionInterpreter.pICalc.dec(Integer.valueOf(this.stang));
		}
		return 0;
	}

}
