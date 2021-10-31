package com.designpatterns.buildermode.product;

import com.designpatterns.buildermode.HouseBuilder;

/**
 * @author YUDI
 * @date 2020/8/15 22:17
 */
public class House {

    private String basic;
    private String walls;
    private String roofed;

    public static class ConcreteBuilder implements HouseBuilder{

        private House house;

        public ConcreteBuilder() {
            this.house = new House();
        }

        @Override
        public HouseBuilder basic(String msg) {
            house.setBasic(msg);
            return this;
        }

        @Override
        public HouseBuilder walls(String msg) {
            house.setWalls(msg);
            return this;

        }

        @Override
        public HouseBuilder roofed(String msg) {
            house.setRoofed(msg);
            return this;
        }

        @Override
        public House createHouse() {
            return house;
        }
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }

    @Override
    public String toString() {
        return "House{" +
                "basic='" + basic + '\'' +
                ", walls='" + walls + '\'' +
                ", roofed='" + roofed + '\'' +
                '}';
    }
}
