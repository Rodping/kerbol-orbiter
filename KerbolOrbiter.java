import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class KerbolOrbiter {
    public static void main(String[] args) {
        
        KerbolSystemSet();
        
    }
    static void KerbolSystemSet(){
        Planet sun = new Planet();
        
        sun.name.set("Kerbol");
        sun.physics.type.set("Sun");
        sun.physics.equat_rad.set(261000000); //meters
        
        //vytvorime planetu
        Planet kerbin = new Planet();
        
        
        kerbin.name.set("Kerbin");
        kerbin.physics.type.set("Planet");
        
        kerbin.physics.equat_rad.set(600000); //meters
        
        //long m = (long) Math.pow(10, 22);
        kerbin.physics.mass.setShorter(5.2915158d, 22);
        //kerbin.physics.mass.add(10);
        kerbin.orbit.ap_pe_apsis.set("both", 13599840256L, 13599840256L);
        //kerbin.orbit.apoapsis.set(13599840256L);
        //kerbin.orbit.periapsis.set(13599840256L);
        
        Planet mun = new Planet();
        
        mun.name.set("Mun");
        mun.physics.type.set("Moon");
        
        mun.physics.equat_rad.set(200000);
        
        mun.physics.mass.setShorter(9.7599066d, 20);
        //mun.physics.mass.add(-3);
        
        mun.orbit.ap_pe_apsis.set("both", 12000000L, 12000000L);
    }
    
}        


class Planet {
    
    String _emoji = "🌐";
    String _name;
    short ID =-1; //-1 = does not exist
    static Short nextFreeID = 0;
    //static String[] Objects = new String[100];
    //static public short[] IDs = new short[100];
    static String prev_selected;
    static ArrayList<String> Objects = new ArrayList<>(); // Inicializace seznamu
    static ArrayList<Short> IDs = new ArrayList<>();
    
    ///PHYSICAL CHARACTERISTICS
    String _type; //type of object
    
    
    int _equator_rad=0; //Equatorial radius
    
    //-- NASTAVENI HMOTNOSTI -- WEIGHT SET
    long local_mass = 0;
    String short_local_mass;
    private double ExpValue = 0; //Shorter number to exponentiate
    private int Exp = 0; //exponent
    
    
    ///ORBITAL CHARACTERISTICS
    long _semimajor_axis;
    long _apoapsis;
    long _periapsis;
    
    //--more information on the picture of orbit char.
    short _orbital_ecc; //Orbital eccentricity
    short _orbital_in; //Orbital inclination
    short _argument_of_periapsis; //Argument of periapsis
    short _longitude; //longitude of the ascending node
    short _sidereal_orbital_per; //time to complete orbit around another object
    short _orbital_vel; //Orbital velosity
    
    
    //Class seter
    public Name name = new Name();
    
    public class Name{
        void set(String newName){
            if(ID == -1){
                ID = nextFreeID;
                IDs.add((Short)(ID));
                nextFreeID++;
                
                _name = newName;
                Objects.add(_name);
                
            }
            else{
                _name = newName;
                Objects.set(ID, _name);
                Objects.add(_name);
                
            }
            for(int i = 0; Objects.size() < i; i++){
                if(Objects.get(i).equals(_name)){}
            }
            _name = newName;
        }
        
        String get(){
            return _name;}
        int getID(){
            return (int)ID;}
    }
    
    
    
    // 1. Musíš vytvořit proměnnou (field), která drží instanci té vnitřní třídy
    // variable (field)
    public Physics physics = new Physics();
    public Orbit orbit = new Orbit();
    
    public class Physics{
        /////////////////////////
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
                System.out.println("\n"+_emoji+_name+", ID: "+ID);
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
        /////////////////////////
        public SemiMajor_Axis semimajor_axis = new SemiMajor_Axis();
        public Ap_Pe_Apsis ap_pe_apsis = new Ap_Pe_Apsis();
        //public Orbital_Arguments orbital_args = new Orbital_Arguments();
        /////////////////////////
        
        class SemiMajor_Axis{
            long get(){
                return _semimajor_axis;
            }
        }
        class Ap_Pe_Apsis{
            void set(String ToSet, long Apo, long Peri){
                if(ToSet == "Ap" || ToSet == "Apoapsis") {
                    _apoapsis = Apo;
                    Orbit.this.print("apoapsis");
                }
                else if(ToSet == "Pe" || ToSet == "Apoapsis"){
                    _periapsis = Peri;
                    Orbit.this.print("periapsis");
                }
                else if(ToSet=="both"){
                    _apoapsis = Apo;
                    _periapsis = Peri;
                    Orbit.this.print("apoapsis");
                    Orbit.this.print("periapsis");
                
                }   
                _apoapsis = Apo;
                
                
                if(_periapsis != 0 || _periapsis != 0){
                    _semimajor_axis = (_apoapsis + _periapsis)/2;
                    Orbit.this.print("semimajor");
                }
            }
            long ApGet(){
                return _apoapsis;
            }
            long PeGet(){
                return _periapsis;
            }
        }
        
        /////////////////////////
        void print(String attr){
            if(!(_name == prev_selected)) {
                System.out.println("\n"+_emoji+_name);}
            prev_selected = _name;
            switch(attr){
                case "all":
                    System.out.println();
                case "semimajor":
                    System.out.println("  ⚪ Semi-major axis: "+_semimajor_axis+" m"); //meters
                    break;
                case "apoapsis":
                    System.out.println("  ⬆️ Apoapsis: "+_apoapsis+" m"); //meters
                    break;
                case "periapsis":
                    System.out.println("  ⬇️ Periapsis: "+_periapsis+" m"); //meters
                    break;
            }
        }
    }
}


