import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SeznamiUV {

    Seznam<Drzavljan> seznamPoImenu;
    Seznam<Drzavljan> seznamPoEmso;
    
    static String memoryError = ">>Error: not enough memory, operation failed";
   
    public SeznamiUV() {
        seznamPoImenu = new Drevo23<Drzavljan>(new DrzavljanPrimerjajPoImenu());
        seznamPoEmso = new Drevo23<Drzavljan>(new DrzavljanPrimerjajPoEmso());
    }

    public String processInput(String input) {
        String token;
        String result = ">>OK";
        String[] params = input.split(" ");

        if (input.equals("")) {
            return "";
        }
        else{
            token = params[0];
        }

        try {
            if (token.equals("add")){
                if (params.length == 1) {
                    result = "add> EMSO: ";
                }
                else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("remove")){
                if (params.length == 2)  {
                    String emso = params[1];
                    if (emsoValid(emso)) {
                        Drzavljan drzavljan = seznamPoEmso.remove(new Drzavljan(emso, "X", "X", 0, "X"));
                        seznamPoImenu.remove(drzavljan);
                    }
                    else {
                        result = ">>Invalid input data";
                    }
                }
                else if (params.length == 1) {
                    result = "remove> NAME: ";
                }
                else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("search")){
                if (params.length == 2) {
                    String emso = params[1];
                    if (emsoValid(emso)) {
                        if (seznamPoEmso.exists(new Drzavljan(emso, "X", "X", 0, "X"))) {
                            result = ">>" + seznamPoEmso.search(new Drzavljan(emso, "X", "X", 0, "X"));
                        }
                        else {
                            result = ">>Person does not exist";
                        }
                    }
                    else {
                        result = ">>Invalid input data";
                    }
                }
                else if (params.length == 1) {
                    result = "search> NAME: ";
                }
                else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("count") || token.equals("size")){
                if (params.length == 1) {
                    result = ">>No. of Persons: " + seznamPoImenu.size();
                }
                else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("reset")){
                if (params.length == 1) {
                    result = "reset> Are you sure (y|n): ";
                }
                else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("print")){
                if (params.length == 1) {
                    if (seznamPoImenu.size() > 0) {
                        result = ">>No. of Persons: " + seznamPoImenu.size() + "\n" + seznamPoImenu.print();
                    }
                    else {
                        result = ">>No. of Persons: 0";
                    }
                }
                else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("save")){
                if (params.length == 2) {
                    seznamPoImenu.save(new FileOutputStream(params[1] + "_p"));
                    seznamPoEmso.save(new FileOutputStream(params[1] + "_e"));
                } else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("restore")){
                if (params.length == 2) {
                    seznamPoImenu.restore(new FileInputStream(params[1] + "_p"));
                    seznamPoEmso.restore(new FileInputStream(params[1] + "_e"));
                } else {
                    result = ">>Invalid argument";
                }
            }
            else if (token.equals("exit")){
                result = ">>Bye";
            }
            else {
                result = ">>Invalid command";
            }

        } catch (UnsupportedOperationException e) {
            result = ">>Invalid command";
        } catch (java.util.NoSuchElementException e) {
            result = ">>Person does not exist";
        } catch (IOException e) {
            result = ">>I/O Error " + e.getMessage();
        } catch (ClassNotFoundException e) {
            result = ">>Unknown format";
        }

        return result;
    }


    public String processEmso(String emso) {
        if (emsoValid(emso)) {
            if (emsoUnique(emso)) {
                return "add> NAME: ";
            }
            else {
                return ">>Person already exists";
            }
        }
        else {
            return ">>Invalid input data";
        }
    }

    public String processIme(String ime) {
        return "add> SURNAME: ";
    }

    public String processPriimek(String ime, String priimek) {
        if (imePriimekUnique(ime, priimek)) {
            return "add> AGE: ";
        }
        else {
            return ">>Person already exists";
        }
    }

    public String processStarost(String starost) {
        if (starostValid(starost)) {
            return "add> VACCINE: ";
        }
        else {
            return ">>Invalid input data";
        }
    }

    public String processCepivo(String cepivo) {
        if (cepivoValid(cepivo)) {
            return "";
        }
        else {
            return ">>Invalid input data";
        }
    }

    public String processAddDrzavljan(String emso, String ime, String priimek, String starost, String cepivo) {
        try {
            seznamPoImenu.add(new Drzavljan(emso, ime, priimek, Integer.parseInt(starost), cepivo));
            seznamPoEmso.add(new Drzavljan(emso, ime, priimek, Integer.parseInt(starost), cepivo));
            return ">>OK";
        }
        catch (OutOfMemoryError e) {
            return memoryError;
        }
    }


    private boolean emsoValid(String emsoInput) {
        Character[] array = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        ArrayList<Character> numbers = new ArrayList<Character>(Arrays.asList(array));
        if (emsoInput.length() != 13) {
            return false;
        }
        for (int i = 0; i < emsoInput.length(); i++) {
            Character character = emsoInput.charAt(i);
            if (!numbers.contains(character)) {
                return false;
            }
        }
        return true;
    }

    private boolean emsoUnique(String emsoInput) {
        if (seznamPoEmso.exists(new Drzavljan(emsoInput, "X", "X", 1, "Pfizer"))) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean imePriimekUnique(String imeInput, String priimekInput) {
        if (seznamPoImenu.exists(new Drzavljan("0000000000000", imeInput, priimekInput, 1, "Pfizer"))) {
            return false;
        }
        else {
            return true;
        }
    }

    private boolean starostValid(String starostInput) {
        try {
            int starost = Integer.parseInt(starostInput);
            if (starost >= 0 && starost < 110) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean cepivoValid(String cepivoInput) {
        String[] array = {"PFIZER", "PFEIZER", "MODERNA", "ASTRAZENECA", "JOHNSON"};
        ArrayList<String> cepiva = new ArrayList<String>(Arrays.asList(array));
        if (cepiva.contains(cepivoInput.toUpperCase())) {
            return true;
        }
        else {
            return false;
        }
    }


    public String processRemoveIme(String ime) {
        return "remove> SURNAME: ";
    }

    public String processRemovePriimek(String ime, String priimek) {
        try {
            Drzavljan drzavljan = seznamPoImenu.remove(new Drzavljan("X", ime, priimek, 0, "X"));
            seznamPoEmso.remove(drzavljan);
            return ">>OK";
        } catch (java.util.NoSuchElementException e) {
            return ">>Person does not exist";
        }
    }

    public String processSearchIme(String ime) {
        return "search> SURNAME: ";
    }

    public String processSearchPriimek(String ime, String priimek) {
        String output;
        if (seznamPoImenu.exists(new Drzavljan("X", ime, priimek, 0, "X"))) {
            output = ">>" + seznamPoImenu.search(new Drzavljan("X", ime, priimek, 0, "X"));
        }
        else {
            output = ">>Person does not exist";
        }
        return output;
    }

    public String processReset(String response) {
        String output;
        if (response.equalsIgnoreCase("y")) {
            output = ">>OK";
            seznamPoEmso.reset();
            seznamPoImenu.reset();
        }
        else if (response.equalsIgnoreCase("n")) {
            output = ">>OK";
        }
        else {
            output = ">>Invalid argument";
        }
        return output;
    }
}
