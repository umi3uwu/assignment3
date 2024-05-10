import java.util.Objects;

class MyCars {
    private String clientId;
    private String carModel;

    public MyCars(String clientId, String carModel) {
        this.clientId = clientId;
        this.carModel = carModel;
    }

    public String getClientId() {
        return clientId;
    }

    public String getCarModel() {
        return carModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCars myCars = (MyCars) o;
        return Objects.equals(clientId, myCars.clientId) &&
                Objects.equals(carModel, myCars.carModel);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + clientId.hashCode();
        result = 31 * result + carModel.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Client's id: " + clientId + ", Car Model: " + carModel;
    }
}