public class MyIpAddress {
    private String name;
    private String ip;
    public int timesChecked;
    private   String status;
    private  boolean hasMessaged;

    public MyIpAddress(String ip, int timesChecked,String status,String name) {
        this.name = name;
        this.ip = ip;
        this.timesChecked = timesChecked;
        this.status = status;
        hasMessaged=false;
    }


    public String getName() {
        return name;
    }

    public String getIp(){
        return  ip;
    }

    public void reset(){
        hasMessaged=false;
        timesChecked=1;
        status="Connection OK";
    }

    public void info(){
        System.out.println(" Ip - "+ip +" - timess Checked "+ timesChecked);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void printStatus(){
        if(status.equals("Connection OK")){
            System.out.println(status+" - "+ip);
        }else{
            System.out.println(status+" #"+ this.timesChecked + " - "+ip);
        }
    }


    public boolean isHasMessaged() {
        return hasMessaged;
    }
    public  void setHasMessaged(boolean hasMessaged) {
        this.hasMessaged = hasMessaged;
    }
}
