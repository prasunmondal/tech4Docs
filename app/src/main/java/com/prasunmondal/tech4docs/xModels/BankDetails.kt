package com.prasunmondal.tech4docs.xModels

class BankDetails {

    // public details (sharable)
    var AccountNumber: String
    var IFSCCode: String
    var BranchName: String
    var MICRCode: String
    var BranchAddress: String
    var AccountHolderName: String
    var UPICodes: ArrayList<String>
    var linkedEmailId: String
    var linkedPhoneNumber: String

    // personal (not sharable)
    var customerId: String
    var netbankingUserid: String
    var netbankingLoginPassword: String
    var netbankingTransactionPassword: String
    var appOpeningPIN: String

    // rarely used personal
    var securityQuestionsNAnswers: Map<String, String>

    constructor(
            AccountNumber: String,
            IFSCCode: String,
            BranchName: String,
            MICRCode: String,
            BranchAddress: String,
            AccountHolderName: String,
            UPICodes: ArrayList<String>,
            linkedEmailId: String,
            linkedPhoneNumber: String,
            customerId: String,
            netbankingUserid: String,
            netbankingLoginPassword: String,
            netbankingTransactionPassword: String,
            appOpeningPIN: String,
            securityQuestionsNAnswers: Map<String, String>
    ) {
        this.AccountNumber = AccountNumber
        this.IFSCCode = IFSCCode
        this.BranchName = BranchName
        this.MICRCode = MICRCode
        this.BranchAddress = BranchAddress
        this.AccountHolderName = AccountHolderName
        this.UPICodes = UPICodes
        this.linkedEmailId = linkedEmailId
        this.linkedPhoneNumber = linkedPhoneNumber
        this.customerId = customerId
        this.netbankingUserid = netbankingUserid
        this.netbankingLoginPassword = netbankingLoginPassword
        this.netbankingTransactionPassword = netbankingTransactionPassword
        this.appOpeningPIN = appOpeningPIN
        this.securityQuestionsNAnswers = securityQuestionsNAnswers
    }
}