package ru.skillbench.tasks.basics.entity;

public class LocationImpl implements Location{

    private String name;
    private Type type;
    private Location parent;

    public LocationImpl(String name, Type type, Location parent){
        this.name = name;
        this.type = type;
        this.parent = parent;
    }

    public LocationImpl(String name, Type type){
        this.name = name;
        this.type = type;
        this.parent = null;
    }

    public LocationImpl(){
        this.parent = null;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public String getParentName() {
        if (this.parent == null) return "--";
        else return this.parent.getName();
    }

    @Override
    public Location getTopLocation() {
        if (this.parent == null) return this;
        else return this.parent.getTopLocation();
    }

    @Override
    public boolean isCorrect() {
        if (this.parent == null) return true;
        else if (this.parent.getType().compareTo(this.getType()) >= 0) return false;
        else return this.parent.isCorrect();
    }

//   @Override
//    public String getAddress() {
//       if (!this.isCorrect()) return "Incorrect address";
//       else{
//           if ((this.getName().endsWith(".")) || (this.pointAndSpace())) {
//               if (this.parent != null) return this.getName() + ", " + this.parent.getAddress();
//               else return this.getName();
//           }
//           else{
//               if (this.parent != null) return this.type.getNameForAddress() + this.getName() +  ", " + this.parent.getAddress();
//               else return this.type.getNameForAddress() + this.getName();
//           }
//       }
//    }

    private boolean pointAndSpace() {
        char[] charName = this.getName().toCharArray();
        boolean hasPoint = false;
        for (char elem : charName) {
            if (elem == '.') hasPoint = true;
            else if (elem == ' ')
                return hasPoint;
        }
        return false;
    }

    @Override
    public String getAddress() {
        if (!this.isCorrect()) return "Incorrect address";
        else{
            if ((this.getName().endsWith(".")) || (this.pointAndSpace())) {
                if (this != this.getTopLocation()) return this.getName() + ", " + this.parent.getAddress();
                else return this.getName();
            }
            else{
                if (this != this.getTopLocation()) return this.type.getNameForAddress() + this.getName() +  ", " + this.parent.getAddress();
                else return this.type.getNameForAddress() + this.getName();
            }
        }
    }

    @Override
    public String toString() {
        return this.name + ' ' + '(' + this.type + ')';
    }
}
