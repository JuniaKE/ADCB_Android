package co.ke.meritsystems.adcb_service_app;

public class URLs {

    private static final String ROOT_URL = "https://licensing.meritsystems.co.ke/api/V1/";
    // AUTH
    public static final String URL_LOGIN = ROOT_URL + "auth/login";
    public static final String LOGOUT = ROOT_URL + "auth/logout";

    //VALIDATIONS
    public static final String INVOICE_VALIDATION = ROOT_URL + "validations/invoices";
    public static final String PAYMENT_VALIDATION = ROOT_URL + "validations/payments";
    public static final String LICENSE_VALIDATION = ROOT_URL + "validations/license";

    //INSPECTION
    public static final String URL_SUB_COUNTIES = ROOT_URL + "inspections/subcounties";
    public static final String URL_SUB_COUNTY_WARDS = ROOT_URL + "inspections/subcounty/wards";
    public static final String URL_WARD_REQUESTS = ROOT_URL + "inspections/ward/requests";
    public static final String URL_REQUEST = ROOT_URL + "inspections/request/";

    // INSPECTIONS
    public static final String INSPECTION_ROOT_URL = ROOT_URL + "inspection/request";
    //Public Health
    public static final String PUBLIC_HEALTH_GET = INSPECTION_ROOT_URL + "public_health";
    public static final String PUBLIC_HEALTH_PUT = INSPECTION_ROOT_URL + "public_health/update";

    //FURNITURE
    public static final String FURNITURE_GET = INSPECTION_ROOT_URL + "furniture";
    public static final String FURNITURE_PUT = INSPECTION_ROOT_URL + "furniture/update";

    //STAFF MEDICAL
    public static final String STAFF_MEDICAL_GET = INSPECTION_ROOT_URL + "staff_medical";
    public static final String STAFF_MEDICAL_PUT = INSPECTION_ROOT_URL + "staff_medical/update";

    //FIRE & SAFETY
    public static final String FIRE_SAFETY_GET = INSPECTION_ROOT_URL + "fire_safety";
    public static final String FIRE_SAFETY_PUT = INSPECTION_ROOT_URL + "fire_safety/update";

    //DISPOSAL
    public static final String DISPOSAL_GET = INSPECTION_ROOT_URL + "disposal";
    public static final String DISPOSAL_PUT = INSPECTION_ROOT_URL + "disposal/update";

    //SANITARY
    public static final String SANITARY_GET = INSPECTION_ROOT_URL + "sanitary";
    public static final String SANITARY_PUT = INSPECTION_ROOT_URL + "sanitary/update";

    //PHYSICAL PLANNING
    public static final String PHYSICAL_PLANNING_GET = INSPECTION_ROOT_URL + "physical_planning";
    public static final String PHYSICAL_PLANNING_PUT = INSPECTION_ROOT_URL + "physical_planning/update";

    //STRUCTURAL CONDITION
    public static final String STRUCTURAL_CONDITION_GET = INSPECTION_ROOT_URL + "structural_condition";
    public static final String STRUCTURAL_CONDITION_PUT = INSPECTION_ROOT_URL + "structural_condition/update";


}
