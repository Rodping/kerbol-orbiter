import java.util.Scanner;
import java.util.Random;

public class KerbolOrbiter {
    public static void main(String[] args) {
        //vytvorime planetu
        Planet kerbin = new Planet();
        
        kerbin.Name("Kerbin");
        
        //long m = (long) Math.pow(10, 22); 
        //kerbin.physics.mass.set((long)(5.2915158 * m));
        //                          Value to Expo  Expo
        kerbin.physics.mass.setShorter(5.2915158, 22);
        //kerbin.physics.mass.add(10);
        
        Planet mun = new Planet();
        
        mun.Name("Mun");
        
        mun.physics.mass.setShorter(9.7599066, 20);
        //mun.physics.mass.add(-3);
    }
    
}        


class Planet {
    
    String name;
    
    //NASTAVENI HMOTNOSTI -- WEIGHT SET
    long local_mass = 0;
    String short_local_mass;
    private double ExpValue = 0; //Cislo ktere se bude exponovat
    private int Exp = 0; //Exponent ktery bude exponovat
    
    
    void Name(String newName){
        name = newName;
    }
    
    
    // 1. Musíš vytvořit proměnnou (field), která drží instanci té vnitřní třídy
    // variable (field)
    public Physics physics = new Physics();
    
    public class Physics{
        public Mass mass = new Mass();
        
        // 2. Definice vnitřní třídy (v Javě raději s velkým písmenem)
        //--inner class
        public class Mass {
                
            void setShorter(double newValue, int Exponent){
                //pomuze rychleji nastavit hmotnost zadanim ExpValue a Exp
                //--helps set faster weight entering ExpValue and Exp
                short_local_mass = Double.toString(newValue) + "*10^"+ Integer.toString(Exponent);
                local_mass = (long)((newValue/1000) * ((long) Math.pow(10, Exponent)));
                
            
                ExpValue = newValue;
                Exp = Exponent;
                
                Physics.this.print("Full_mass");
                
            
            }
            void set(long newValue){
                local_mass = newValue;
                System.out.println("🌐"+name+"\n  ⚖️ new mass: "+newValue);
            }
            void add(long newValue){//increases the mass 
                local_mass = local_mass + newValue;
                System.out.println(""+name+"\n  ⚖️ added mass: "+newValue+"\n  ⚖️ new mass: "+local_mass);
            }
            
            
        }  
        void print(String attr){
            switch(attr){//F### - cely vypis
                case "all":
                    System.out.println();
                case "mass":
                    System.out.println("\n🌐"+name+"\n  ⚖️ local mass: "+local_mass+" t"); //tonne - 1000 kg
                case "Full_mass":
                    System.out.println("\n🌐"+name+
                                        "\n  ⚖️ local mass: "+local_mass+" t"+
                                        "\n  ⚖️ Shorter mass: "+short_local_mass+" kg");
            }
        }
    }        
}
    


