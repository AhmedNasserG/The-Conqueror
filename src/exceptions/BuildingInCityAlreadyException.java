package exceptions;

public class BuildingInCityAlreadyException extends BuildingException {
    public BuildingInCityAlreadyException() {
    }

    public BuildingInCityAlreadyException(String s) {
        super(s);
    }
}
