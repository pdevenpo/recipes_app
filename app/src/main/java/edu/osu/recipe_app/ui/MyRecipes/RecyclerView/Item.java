package edu.osu.recipe_app.ui.MyRecipes.RecyclerView;

public class Item {

    private String name;
    private String tags;

    public Item(String name, String tags){
        this.name = name;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLength() {
        return tags;
    }

    public void setLength(String tags) {
        this.tags = tags;
    }
}
