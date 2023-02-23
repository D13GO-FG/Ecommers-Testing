package dataObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"title", "price", "description", "categoryId", "images"})
public class ItemDetails {
    private String title;
    private int price;
    private String description;
    private int categoryId;
    private List<String> images;

    public ItemDetails(String title, int price, String description, int categoryId, List<String> images){
        this.title = title;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.images = images;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("price")
    public int getPrice() {
        return price;
    }
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }
    @JsonProperty("categoryId")
    public int getCategoryId() {
        return categoryId;
    }
    @JsonProperty("images")
    public List<String> getImages() {
        return images;
    }
}
