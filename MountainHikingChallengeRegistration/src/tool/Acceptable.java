package tool;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Namle
 */
public interface Acceptable {
    public final String STU_ID_VALID = "^[CcDdHhSsQq][Ee]-?[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
    public final String CAMPUS_CODE_VALID = "^[CcDdHhSsQq][Ee]$";
    public final String NAME_VALID ="^.{2,20}$";
    public final String DOUBLE_VALID ="^\\d+\\.\\d+$";
    public final String INTEGER_VALID ="\\d+$";
    public final String PHONE_VALID ="^0[3-9]\\d{8}$";
    public final String VIETTEL_VALID ="0(86|96|97|98|39|38|37|36|35|34|33|32)\\\\d{7}$";
    public final String VNPT_VALID ="^0(91|94|83|84|85|81|82|88)\\\\d{7}$";
    public final String EMAIL_VALID ="^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    
    
    public static boolean isValid(String data, String pattern){
        return data.matches(pattern);
    }
}
