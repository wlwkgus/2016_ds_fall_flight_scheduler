/**
 * Created by tonykim on 11/21/16.
 */
public class Date {
    private int hour;
    private int minute;

    public Date(String dateString){
        hour = Integer.parseInt(dateString.substring(0, 2));
        minute = Integer.parseInt(dateString.substring(2, 4));;
        standardize();
        if(hour >= 24){
            hour -= 24;
        }
    }

    public Date(String dateString, boolean isStandard){
        hour = Integer.parseInt(dateString.substring(0, 2));
        minute = Integer.parseInt(dateString.substring(2, 4));;
        standardize();
        if(isStandard){
            if(hour >= 24){
                hour -= 24;
            }
        }
    }

    public Date(String dateString, Date date){
        hour = Integer.parseInt(dateString.substring(0, 2));
        minute = Integer.parseInt(dateString.substring(2, 4));;
        standardize();
        if(hour >= 24){
            hour -= 24;
        }

        for(; this.getString().compareTo(date.getString()) < 0; this.plus(1440));
    }

    private void standardize(){
        if(minute > 60) {
            int quot = minute / 60;
            hour += quot;
            minute -= quot * 60;
        }
    }

    public Date plus(int minute){
        this.minute += minute;
        standardize();
        return this;
    }

    public Date plus(Date date){
        this.minute += date.minute;
        this.hour += date.hour;
        standardize();
        return this;
    }

    public Date plus(String dateString){
        Date temp = new Date(dateString);
        this.plus(temp);
        return this;
    }

    private String getStringInTwoDigits(int number){
        if(number == 0) return "00";
        else if (number / 10 == 0) return "0" + number;
        else return Integer.toString(number);
    }

    public String getString(){
        return getStringInTwoDigits(hour) + getStringInTwoDigits(minute);
    }

    public int compareTo(Date date){
        if(this.hour > date.hour) return 1;
        else if(this.hour < date.hour) return -1;
        else{
            if(this.minute > date.minute) return 1;
            else if(this.minute < date.minute) return -1;
            else return 0;
        }
    }

    public static void main(String args[]){
        Date temp = new Date("0945");
        System.out.println(temp.getString());
    }
}
