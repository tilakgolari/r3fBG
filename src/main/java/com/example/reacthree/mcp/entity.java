package com.example.reacthree.mcp;

public class entity {

    private String meshName;
    private String color;

    public String getMeshName() {
        return meshName;
    }

    public void setMeshName(String meshName) {
        this.meshName = meshName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public entity(String meshName, String color) {
        this.meshName = meshName;
        this.color = color;
    }

    public entity() {
    }
}
