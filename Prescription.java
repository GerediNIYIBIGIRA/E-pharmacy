import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Prescription {
    private String prescriptionID;
    private String customerID;
    private String doctorName;
    private ArrayList<Medication> medications;
    private LocalDate date;
    private static JSONArray prescriptionList;


    public Prescription() {
        prescriptionList = new JSONArray();
    }

    public Prescription(String _prescriptionID, String _customerID, String _doctorName, ArrayList<Medication> _medication) {
        prescriptionID = _prescriptionID;
        customerID = _customerID;
        doctorName = _doctorName;
        medications = _medication;
        date = LocalDate.now();
    }

    // TODO: Add code to help you to create object/instance for this class in different way

    public Prescription(String prescriptionID, String customerID, String doctorName, ArrayList<Medication> medications, LocalDate date) {
        this.prescriptionID = prescriptionID;
        this.customerID = customerID;
        this.doctorName = doctorName;
        this.medications = medications;
        this.date = date;
    }


    // TODO: Add code to help you to access or modify data members for this class

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(String prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public ArrayList<Medication> getMedications() {
        return medications;
    }

    public void setMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static JSONArray getPrescriptionList() {
        return prescriptionList;
    }

    public static void setPrescriptionList(JSONArray prescriptionList) {
        Prescription.prescriptionList = prescriptionList;
    }


    // TODO: Add code needed to be able to add prescription in the file
    // While adding the prescription in the file, please follow the format shown below
    // Format for the prescription: {"DoctorName":"Yves","PrescriptionID":"TA3","Medications":[{"quantity":2,"processedStatus":false,"name":"IBUPROFEN","id":"IB7"}],"CustomerID":"GR","Date":"2023-08-07"}

    public void addPrescription() throws IOException, ParseException {

        FileHandler fileHandler = new FileHandler();
        JSONArray existingPrescriptions = fileHandler.readJSONArrayFromFile();
        HashMap<String, Object> mapPrescription = new HashMap<>();
        mapPrescription.put("PrescriptionID", prescriptionID);
        mapPrescription.put("CustomerID", customerID);
        mapPrescription.put("DoctorName", doctorName);
        mapPrescription.put("Medications", medications);
        mapPrescription.put("Date", date.toString());


        // TODO: Add code to add prescription in the file


        existingPrescriptions.add(mapPrescription);

        fileHandler.writeJSONArrayToFile(existingPrescriptions);
    }


    // TODO: Add code needed to be able to get all medications on the prescription
    // TODO: You must return an array of medications!


    private JSONArray getMedicationsOnPrescription(Prescription prescription) {
        JSONArray jsonArray = new JSONArray();


        // TODO: Add code to get medications on the prescription
        JSONObject medicarionObject = new JSONObject();
        for (Medication medication : prescription.getMedications()
        ) {
            JSONObject medicationObject= new JSONObject();
            medicationObject.put("ID",medication.getID());
            medicationObject.put("Name", medication.getName());
            medicationObject.put("Details", medication.getDetails());
            medicationObject.put("Dosage", medication.getDosage());


        return jsonArray;


    }


    // TODO: Add code to help you viewing all prescriptions in the file
    // You must return an array of prescriptions


    public ArrayList<Prescription> viewPrescription() throws IOException, ParseException {
        // TODO: Add code to help you reading from the prescriptions.json file

        JSONArray jsonArray = fileHandler.readJSONArrayFromFile();


        // TODO: Add code to help you creating an array of prescriptions


        for (Object obj : jsonArray) {
            JSONObject jsonObject = (JSONObject) obj;

            String doctorName = (String) jsonObject.get("DoctorName");
            String prescriptionID = (String) jsonObject.get("PrescriptionID");
            String customerID = (String) jsonObject.get("CustomerID");
            String date = (String) jsonObject.get("Date");
            LocalDate dateToPrint = LocalDate.parse(date);

            ArrayList<Medication> medications = new ArrayList<>();

            JSONArray medicationsArray = (JSONArray) jsonObject.get("Medications");

            for (Object medObj : medicationsArray) {
                JSONObject medication = (JSONObject) medObj;

                // TODO: Add code to get medication ID, name and quantity
                // medication quantity should be casted to int
                // also medication ID and name should be casted to String

                medications.add(new Medication(medicationID, medicationName, quantity));
            }

            prescriptions.add(new Prescription(prescriptionID, customerID, doctorName, dateToPrint, medications));

        }

    }


    // TODO: Add code to help you deleting a specific prescription
    public void deletePrescription() throws IOException, ParseException {
        // TODO: Add code to help you reading from the prescriptions.json file


        int indexToDelete = -1;
        for (int i = 0; i < existingPrescriptions.size(); i++) {
            JSONObject jsonObject = (JSONObject) existingPrescriptions.get(i);
            String existingPrescriptionID = (String) jsonObject.get("PrescriptionID");

            // TODO: Add code to check if the prescription you want to delete is similar to one exists
            if () {
                indexToDelete = i;
                break;
            }
        }

        if (indexToDelete != -1) {
            existingPrescriptions.remove(indexToDelete);
            fileHandler.writeJSONArrayToFile(existingPrescriptions);
        }
    }

}

