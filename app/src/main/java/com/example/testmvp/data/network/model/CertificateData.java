package com.example.testmvp.data.network.model;

public class CertificateData {
/*
    {
        "CertificateTypeId":1,
            "OrganizationDescription":"ttt",
            "OrganizationId":1,
            "RequestReason":"gffg",
            "RequestUser":"عليرضا شريفي"
    }*/


    Integer CertificateTypeId;
    String OrganizationDescription;
    Integer OrganizationId;
    String RequestReason;
    String RequestUser;

    public CertificateData(Integer certificateTypeId, String organizationDescription, Integer organizationId, String requestReason, String requestUser) {
        CertificateTypeId = certificateTypeId;
        OrganizationDescription = organizationDescription;
        OrganizationId = organizationId;
        RequestReason = requestReason;
        RequestUser = requestUser;
    }

    public CertificateData() {
    }

    public Integer getCertificateTypeId() {
        return CertificateTypeId;
    }

    public void setCertificateTypeId(Integer certificateTypeId) {
        CertificateTypeId = certificateTypeId;
    }

    public String getOrganizationDescription() {
        return OrganizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        OrganizationDescription = organizationDescription;
    }

    public Integer getOrganizationId() {
        return OrganizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        OrganizationId = organizationId;
    }

    public String getRequestReason() {
        return RequestReason;
    }

    public void setRequestReason(String requestReason) {
        RequestReason = requestReason;
    }

    public String getRequestUser() {
        return RequestUser;
    }

    public void setRequestUser(String requestUser) {
        RequestUser = requestUser;
    }
}
