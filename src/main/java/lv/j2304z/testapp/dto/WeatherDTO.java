package lv.j2304z.testapp.dto;

/**
 * Created by jekaterina.zaiceva on 26.03.15.
 */
public class WeatherDTO {

    private int temperature;
    private int windSpeed;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }
}
