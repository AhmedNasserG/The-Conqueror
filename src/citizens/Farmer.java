package citizens;

import java.util.ArrayList;

public class Farmer extends Citizen {
    private ArrayList<String> tools;

    public Farmer(String name, int age)
    {
        super(name,age);
        tools = new ArrayList<>();
    }

    public ArrayList<String> getTools() {
        return tools;
    }
}
