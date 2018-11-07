package edu.osu.recipe_app.ui.TodaysPick.RecyclerView;

public class ItemToday {

    private String name;
    private String tags;

    public ItemToday(String name, String tags){
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
