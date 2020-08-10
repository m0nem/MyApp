package com.example.testmvp.data.network.model;

public class ApiFormData {
    /*{
        "PersonalNumber": "8922978",
            "NameFamily": "مرتضي بندقيري",
            "BirthCertificateNo": "15768",
            "NationalCode": "1757246411",
            "CertificateIssued": "اهواز",
            "EmploymentDate": "1389/10/01",
            "TotalSalary": " 53,967,047",
            "Organization": "بانک سپه  شعبه  ttt",
            "EmployeeGender": "آقاي",
            "EmploymentType": "قراردادي",
            "CertificateTypeCode": "1",
            "CertificateNumber": null,
            "PrintDescription": false,
            "RequestDescription": null,
            "PrintDate": "0001-01-01T00:00:00",
            "ShowPreview": 0
    }*/

    Integer PersonalNumber;
    Integer BirthCertificateNo;
    Integer NationalCode;
    Integer CertificateTypeCode;
    Integer ShowPreview;
    Integer CertificateNumber;

    Boolean PrintDescription;

    String NameFamily;
    String CertificateIssued;
    String TotalSalary;
    String Organization;
    String EmployeeGender;
    String EmploymentType;
    String RequestDescription;
    String PrintDate;

    public ApiFormData() {
    }

    public ApiFormData(Integer personalNumber, Integer birthCertificateNo, Integer nationalCode, Integer certificateTypeCode, Integer showPreview, Integer certificateNumber, Boolean printDescription, String nameFamily, String certificateIssued, String totalSalary, String organization, String employeeGender, String employmentType, String requestDescription, String printDate) {
        PersonalNumber = personalNumber;
        BirthCertificateNo = birthCertificateNo;
        NationalCode = nationalCode;
        CertificateTypeCode = certificateTypeCode;
        ShowPreview = showPreview;
        CertificateNumber = certificateNumber;
        PrintDescription = printDescription;
        NameFamily = nameFamily;
        CertificateIssued = certificateIssued;
        TotalSalary = totalSalary;
        Organization = organization;
        EmployeeGender = employeeGender;
        EmploymentType = employmentType;
        RequestDescription = requestDescription;
        PrintDate = printDate;
    }

    public Integer getPersonalNumber() {
        return PersonalNumber;
    }

    public void setPersonalNumber(Integer personalNumber) {
        PersonalNumber = personalNumber;
    }

    public Integer getBirthCertificateNo() {
        return BirthCertificateNo;
    }

    public void setBirthCertificateNo(Integer birthCertificateNo) {
        BirthCertificateNo = birthCertificateNo;
    }

    public Integer getNationalCode() {
        return NationalCode;
    }

    public void setNationalCode(Integer nationalCode) {
        NationalCode = nationalCode;
    }

    public Integer getCertificateTypeCode() {
        return CertificateTypeCode;
    }

    public void setCertificateTypeCode(Integer certificateTypeCode) {
        CertificateTypeCode = certificateTypeCode;
    }

    public Integer getShowPreview() {
        return ShowPreview;
    }

    public void setShowPreview(Integer showPreview) {
        ShowPreview = showPreview;
    }

    public Integer getCertificateNumber() {
        return CertificateNumber;
    }

    public void setCertificateNumber(Integer certificateNumber) {
        CertificateNumber = certificateNumber;
    }

    public Boolean getPrintDescription() {
        return PrintDescription;
    }

    public void setPrintDescription(Boolean printDescription) {
        PrintDescription = printDescription;
    }

    public String getNameFamily() {
        return NameFamily;
    }

    public void setNameFamily(String nameFamily) {
        NameFamily = nameFamily;
    }

    public String getCertificateIssued() {
        return CertificateIssued;
    }

    public void setCertificateIssued(String certificateIssued) {
        CertificateIssued = certificateIssued;
    }

    public String getTotalSalary() {
        return TotalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        TotalSalary = totalSalary;
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getEmployeeGender() {
        return EmployeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        EmployeeGender = employeeGender;
    }

    public String getEmploymentType() {
        return EmploymentType;
    }

    public void setEmploymentType(String employmentType) {
        EmploymentType = employmentType;
    }

    public String getRequestDescription() {
        return RequestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        RequestDescription = requestDescription;
    }

    public String getPrintDate() {
        return PrintDate;
    }

    public void setPrintDate(String printDate) {
        PrintDate = printDate;
    }
}
