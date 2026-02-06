import java.util.Scanner;
import java.util.Random;

public class KerbolOrbiter {
    public static void main(String[] args) {
        //vytvorime planetu
        Planet kerbin = new Planet();
        
        kerbin.name.set("Kerbin");
        kerbin.physics.type.set("Planet");
        
        kerbin.physics.equat_rad.set(600000); //meters
        
        //long m = (long) Math.pow(10, 22); 
        //kerbin.physics.mass.set((long)(5.2915158 * m));
        //                          Value to Expo  Expo
        kerbin.physics.mass.setShorter(5.2915158, 22);
        //kerbin.physics.mass.add(10);
        
        
        Planet mun = new Planet();
        
        mun.name.set("Mun");
        mun.physics.type.set("Moon");
        
        mun.physics.equat_rad.set(200000);
        
        mun.physics.mass.setShorter(9.7599066, 20);
        //mun.physics.mass.add(-3);
        
        
    }
    
}        


class Planet {
    
    String _emoji = "🌐";
    String _name;
    static String prev_selected;
    
    String _type;
    
    int _equator_rad=0; //Equatorial radius
    
    //NASTAVENI HMOTNOSTI -- WEIGHT SET
    long local_mass = 0;
    String short_local_mass;
    private double ExpValue = 0; //Cislo ktere se bude exponovat
    private int Exp = 0; //Exponent ktery bude exponovat
    
    
    public Name name = new Name();
    
    public class Name{
        void set(String newName){
            _name = newName;}
        String get(){
            return _name;}
    }
    
    
    
    
    // 1. Musíš vytvořit proměnnou (field), která drží instanci té vnitřní třídy
    // variable (field)
    public Physics physics = new Physics();
    public Orbit orbit = new Orbit();
    
    public class Physics{
        public Type type = new Type(); // type of object - sun, planet, gas giant
        public EquatRad equat_rad = new EquatRad();
        public Mass mass = new Mass();
        
        /////////////////////////
        
        public class Type{
            void set(String typeSet){
                // Sun, Planet, Moon, Gas, Asteroid - 5
                if(typeSet != ""){
                    _type = typeSet;
                    if(typeSet == "Sun"){
                        _emoji = "☀️";
                    }
                    else if(typeSet == "Planet"){
                        _emoji = "🌐";
                    }
                    else if(typeSet == "Moon"){
                        _emoji = "🌔";
                    }
                    else if(typeSet == "Gas"){
                        _emoji = "🪐";
                    }
                    else if(typeSet == "Asteroid"){
                        _emoji = "☄️";
                    }
                    else{
                        _type = "";
                        _emoji = "❌";
                    }
                }
            }
        }
    
        
        public class EquatRad {
            void set(int newValue){
                _equator_rad = newValue;
                Physics.this.print("equatrad");
                
            }
        }
        
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
                //System.out.println("🌐"+_name+"\n  ⚖️ new mass: "+newValue);
                Physics.this.print("mass");
            }
            void add(long newValue){//increases the mass 
                local_mass = local_mass + newValue;
                System.out.println(""+_name+"\n  ⚖️ added mass: "+newValue+"\n  ⚖️ new mass: "+local_mass);
            }
            
            
        }  
        
        
        void print(String attr){
            if(!(_name == prev_selected)) 
                System.out.println("\n"+_emoji+_name);
            prev_selected = _name;
            switch(attr){//F### - cely vypis
                case "all":
                    System.out.println();
                case "equatrad":
                    System.out.println("  📏 Equatorial radius: "+_equator_rad+" m"); //meters
                    break;
                case "mass":
                    System.out.println("  ⚖️ Local mass: "+local_mass+" t"); //tonne - 1000 kg
                    break;
                case "Full_mass":
                    System.out.println("  ⚖️ Local mass: "+local_mass+" t"+
                                       "\n  ⚖️ Shorter mass: "+short_local_mass+" kg");
                    break;
                    
            }
        }
    }        
    public class Orbit{
        
    }
}
    


