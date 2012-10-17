package MuPy;

import java.security.SecureRandom;
import java.math.BigInteger;
import java.util.LinkedList;

class Value {

    private String  type;
    private String  svalue = "";
    private Integer ivalue = 0;

    public Value(String  v) { type = "string"; svalue = v; }
    public Value(Integer v) { type = "int";    ivalue = v; }

    public String getType()  { return type; }
    
    // Once you get a value, you must cast according to its type.
    public Object getValue() {
        Object o = null; 
        if (type == "string") { o = svalue; }
        if (type == "int"   ) { o = ivalue; }
        return o;
    }

    public String toString() {
        String s = null;
        if (type == "string") { s = svalue; }
        if (type == "int"   ) { s = ivalue.toString(); }
        return s;
    }

    public boolean isString() { return (type == "string"); }
    
    public boolean isInt()    { return (type == "int");    }

}

class OurSymbol {

    private String name;
    private Value  value;

    public OurSymbol(String name, Value value) {
        this.name  = name;
        this.value = value;
    }

    public Value  getValue()  { return value;              }
    
    public String getType()   { return value.getType();    }
    
    public String getName()   { return name;               }
    
    public void setValue(Value v) { this.value = v; }

}

class SymbolTable {

    private LinkedList<OurSymbol> table;
    private int lastAnon;

    public SymbolTable() {
        lastAnon = 0;
        table    = new LinkedList<OurSymbol>();
    }

    // 0: Ok
    // 1: Variable ya declarada del mismo tipo
    // 2: Variable declarada de distinto tipo
    public int addVar(String name, Value value) {
        
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).getName().compareTo(name) == 0 &&
                table.get(i).getType().equals(value.getType()))
                return 1;
            if (table.get(i).getName().compareTo(name) == 0 &&
                !(table.get(i).getType().equals(value.getType())))
                return 2;
        }

        table.add(new OurSymbol(name, value));
        return 0;
    }

    public String addAnonVar(Value value) {

        int    res  = 1;
        String name = "__anon" + lastAnon++;

        res = addVar(name, value);
        return name;
    }

    public boolean isDeclared(String name) {
        for(int i = 0; i < table.size(); i++) {
            if(table.get(i).getName().compareTo(name) == 0)
                return true;
        }

        return false;
    }

    public OurSymbol get(String name) {
        for(int i = 0; i < table.size(); i++) {
            if(table.get(i).getName().compareTo(name) == 0)
                return table.get(i);
        }

        return null;
    }

    public Value getValue(String name) {
        for(int i = 0; i < table.size(); i++) {
            if(table.get(i).getName().compareTo(name) == 0)
                return table.get(i).getValue();
        }
        return null;
    }

    public void setValue(String name, Value value) {

        int pos = -1;

        for(int i = 0; i < table.size(); i++) {
            if(table.get(i).getName().compareTo(name) == 0) {
                pos = i;
                break;
            }
        }

        if(pos == -1)
            return;

        table.get(pos).setValue(value);
    }

    public void print() {

        int nameMaxLength = 0, valueMaxLength = 0;
        LinkedList<String[]> symbolStrings = new LinkedList<String[]>();
        String[]  x;
        OurSymbol symbol;
        Value     value;
        String    name        = "";
        String    type        = "";
        String    valuestring = ""; // string representation of the value contents
        String    stringvalue = ""; // string value
        Integer   intvalue;    // integer value

        for (int i = 0; i < table.size(); i++) {
            symbol = table.get(i);
            name   = symbol.getName();
            value  = symbol.getValue();
            type   = symbol.getType();

            if (value.isString()) { 
                stringvalue = (String) value.getValue(); 
                valuestring = stringvalue;
            }
            if (value.isInt()) { 
                intvalue    = (Integer)value.getValue(); 
                valuestring = intvalue.toString();
            }

            if (name.length() > nameMaxLength) { nameMaxLength = name.length(); }
            if (valuestring.length() > valueMaxLength) { valueMaxLength = valuestring.length(); }

            x = new String[3];
            x[0] = name;
            x[1] = type;
            x[2] = valuestring;
            symbolStrings.add(x);
        }
        for (int i = 0; i < symbolStrings.size(); i++) {
            x = symbolStrings.get(i);            
            System.out.print(String.format("Name: %"+nameMaxLength+"s\t| Type: %6s\t| Value: %"+valueMaxLength+"s\n", x[0], x[1], x[2]));
        }
    }
}