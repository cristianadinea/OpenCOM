package brain;

import java.util.StringTokenizer;
import java.util.Vector;

public class Interpretor {

    private String formula;
    private int parantezeMax = 0;

    public Interpretor() {
        this.formula = "";
    }

    public Interpretor(String s) {
        this.formula = s;
    }

	public int interpret() {
		System.out.println("am inceput\n");
		String str = "";
		Vector<String> vect = new Vector<String>();

		// construim elementele
		int[] v = new int[20];
		for (int col = 0;col<20;col++)
			v[col] = 0;
			
		int paran = 0;
		StringTokenizer tokenizer = new StringTokenizer(this.formula);
		while (tokenizer.hasMoreTokens()) {
			str = tokenizer.nextToken();
			if (str.equals("(")) {
				paran++;
				v[paran]++;
			} else if (str.equals(")")) {
				paran--;				
			}						
			vect.add(str);
		}
		
		// aflam nivelul maxim de incuibare		 
		for (int col = 19;col>0;col--)
		{			
			if (v[col]>0) {
				parantezeMax = col;
				break;
			}
		}		
		System.out.println("Nivelul maxim este : " + parantezeMax);
		
		int paranteze = 0;
		int rez = 0;
		int i = 0;
		while (vect.size()>1 && i<vect.size()) {			
		    // nu avem paranteze
		    if ((i == vect.size()-1) || (parantezeMax < 1)) {
		        int nr = 0;
                Vector<String> fact = new Vector<String>();
                while (nr < vect.size()) {
                    fact.add(vect.elementAt(nr));
                    nr++;
                }                
                rez = analize(fact);
                break;
		    }		    
			str = vect.elementAt(i);
			if (str.equals("(")) {
				paranteze++;
				i++;
			} else if (str.equals(")")) {
                paranteze--;
                i++;
            } else {
                // daca suntem pe ultimul nivel 
                if (paranteze == parantezeMax) {
                    int nr = i;
                    Vector<String> fact = new Vector<String>();
                    while ((nr < vect.size()) && !vect.elementAt(nr).equals(")")) {
                        fact.add(vect.elementAt(nr));
                        nr++;
                    }
                    // adaugam rezultatul
                    System.out.println(printVector(vect));
                    System.out.println("i = " + i);
                    System.out.println("nr = " + nr);
                    System.out.println("size = " + vect.size());

                    //System.out.println("rezultatul este inserat in locul lui " + vect.elementAt(i-1));
                    vect.remove(i-1);
                    vect.add(i-1, String.valueOf(analize(fact)));                    
                    System.out.println("rezultatul este : " + vect.elementAt(i-1));
                    //System.out.println("dupa rezultat urmeaza " + vect.elementAt(i));
					
                    // stergem elementele procesate
                    
                    System.out.println(printVector(vect));
                    int erased = nr-i+1;                    
                    System.out.println("nr de elemente sterse este : " + erased);
                    int size = vect.size();                    
                    System.out.println("capacitatea vectorului este : " + size);                                        
                    
                    int j = 0;
                    for (; j < size - nr-1; j++) {
                        System.out.println("I asta a fost " + vect.elementAt(i + j));
                        vect.add(i + j, vect.elementAt(nr + 2 * j + 1));
                        System.out.println("I asta ramane " + vect.elementAt(i + j));
                    }
                    System.out.println(printVector(vect));
                                        
                    while (vect.size()> i + size - nr-1) {                        
                        System.out.println("II asta a fost " + vect.elementAt(vect.size()-1));
                        vect.remove(vect.size()-1);
                        System.out.println("II asta ramane " + vect.elementAt(vect.size()-1));                        
                    }
                    System.out.println(printVector(vect));
                    i = 0;
                    paranteze = 0;
                    v[parantezeMax]--;
                    if (v[parantezeMax]==0)
                    	parantezeMax--;
                } else {
                    i++;
                }
            }
        }

		if (paranteze != 0)
			System.out.println("nu sunt paranteze destule\n");
		System.out.println("gata + " + rez);
		return rez;
	}

	public String printVector(Vector<String> v) {
	    String s = "";
	    for (int i = 0;i<v.size();i++) {
	        s+=" " + v.elementAt(i);
	    }
	    return s;
	} 
	
	public int analize(Vector<String> fact) {
		System.out.println("\n Procesam un nou nivel\n");
		System.out.println(printVector(fact));
		int i = 0;
		while (fact.size() > 1 && i < fact.size()) {

			String str = fact.elementAt(i);
			if (str.equals("--") || (str.equals("++"))) {
				int rez = 0;
				Operatie operatie = new Operatie();
				operatie.setOperatie(str);
				operatie.addStang(String.valueOf(fact.elementAt(i - 1)));
				rez += operatie.operate();

				System.out.println("rezultatul este inserat in locul lui "
						+ fact.elementAt(i - 1));
				fact.remove(i - 1);
				fact.add(i - 1, String.valueOf(rez));
				fact.remove(i);
				System.out
						.println("rezultatul este : " + fact.elementAt(i - 1));
				i = 0;
				System.out.println(printVector(fact));
				continue;
			}
			i++;
		}

		i = 0;
		while (fact.size() > 1 && i < fact.size()) {

			String str = fact.elementAt(i);
			if (str.equals("*") || str.equals("/")) {
				int rez = 0;
				Operatie operatie = new Operatie();
				operatie.setOperatie(str);
				operatie.addStang(String.valueOf(fact.elementAt(i - 1)));
				operatie.addDrept(String.valueOf(fact.elementAt(i + 1)));
				rez += operatie.operate();

				System.out.println("rezultatul este inserat in locul lui "
						+ fact.elementAt(i - 1));
				fact.remove(i - 1);
				fact.add(i - 1, String.valueOf(rez));
				fact.remove(i);
				fact.remove(i);
				System.out
						.println("rezultatul este : " + fact.elementAt(i - 1));
				i = 0;
				System.out.println(printVector(fact));
				continue;
			}
			i++;
		}

		i = 0;
		while (fact.size() > 1 && i < fact.size()) {

			String str = fact.elementAt(i);
			if (str.equals("+") || str.equals("-")) {
				int rez = 0;
				Operatie operatie = new Operatie();
				operatie.setOperatie(str);
				operatie.addStang(String.valueOf(fact.elementAt(i - 1)));
				operatie.addDrept(String.valueOf(fact.elementAt(i + 1)));
				rez += operatie.operate();

				System.out.println("rezultatul este inserat in locul lui "
						+ fact.elementAt(i - 1));
				fact.remove(i - 1);
				fact.add(i - 1, String.valueOf(rez));
				fact.remove(i);
				fact.remove(i);
				System.out
						.println("rezultatul este : " + fact.elementAt(i - 1));
				i = 0;
				System.out.println(printVector(fact));
				continue;
			}
			i++;
		}
		if (fact.size() > 1) {
			System.out.println("Expresie gresita!!!\n");
			System.exit(0);
		}
		
		return Integer.valueOf(fact.elementAt(0));
	}

}
