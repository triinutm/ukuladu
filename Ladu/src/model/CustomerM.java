package model;

public class CustomerM {
        private int id;
        private String name;
        private String type;
        
        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public CustomerM(){}
        
        public void setId(int id){
                this.id = id;
        }
        
        public void setName(String name){
                this.name = name;
        }
        
        public int getId(){
                return id;
        }
        
        public String getName(){
                return name;
        }

}