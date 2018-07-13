package worldcupapp;

import javafx.util.StringConverter;

import java.util.Arrays;

public class NumberConverter extends StringConverter
{
    @Override
    public String toString(Object object)
    {
        if (object instanceof Integer)
        {
            Integer o = (Integer) object;
            return o.toString();

        }
        return null;
    }

    @Override
    public Object fromString(String string)
    {

        if ((Arrays.stream(new String[]{"+"}).parallel().anyMatch(string::contains)))
            return Expresssion.evaluate(string);


        return (string == "") ? 0 : Integer.parseInt(string);

    }


}
