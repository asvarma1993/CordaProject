package com.template.webserver.project;

import com.template.*;
import net.corda.core.concurrent.CordaFuture;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.crypto.SecureHash;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.messaging.DataFeed;
import net.corda.core.node.NodeInfo;
import net.corda.core.node.services.vault.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.Vault;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import rx.Observable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Define your API endpoints here.
 */
@RestController
@RequestMapping("/") // The paths for HTTP requests are relative to this base path.
public class Controller {
    private final CordaRPCOps proxy;
    private final static Logger logger = LoggerFactory.getLogger(Controller.class);
    //  private QueryCriteria lenderCriteria;


    public Controller(NodeRPCConnection rpc) {
        this.proxy = rpc.proxy;
    }
    @Autowired
    LoCApplicationSerivce loCApplicationSerivce;

    @GetMapping(value = "/templateendpoint", produces = "text/plain")
    private String templateendpoint() {
        return "Define an endpoint here.";
    }

    @GetMapping(value="/getAllNodes")
    private ResponseEntity<List<String>> getNodeInfo() {
        List<NodeInfo> nodeInfo=proxy.networkMapSnapshot();
        List<String> nodeList= new ArrayList();
        nodeInfo.forEach(node -> nodeList.add(node.getLegalIdentitiesAndCerts().get(0).getParty().toString()));

        return new ResponseEntity<List<String>>(nodeList, HttpStatus.OK);
    }

    @GetMapping(value="/getMyInfo")
    private ResponseEntity<String> getMyInfo() {
        List<NodeInfo> nodeInfo=proxy.networkMapSnapshot();
        String nodeName=null;
        if(nodeInfo!=null && nodeInfo.size()!=0){
            nodeName=nodeInfo.get(0).getLegalIdentitiesAndCerts().get(0).getName().toString();
        }

        return new ResponseEntity<String>(nodeName, HttpStatus.OK);
    }

    @GetMapping(value = "/getLoCApplications" )
    private List<LoCApplicationState> getLoCApplications()  {
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();
        for(StateAndRef<LoCApplicationState> res : response){
            results.add(res.getState().getData());
        }
        //  System.out.println(proxy.vaultQuery(LoCApplicationState.class).getTotalStatesAvailable());
        //   List<StateAndRef<LoCApplicationState>> states=proxy.vaultQuery(LoCApplicationState.class).component1();
        return results;
        //   return new ResponseEntity<List<StateAndRef<LoCApplicationState>>>(states, HttpStatus.OK);
    }

    @GetMapping(value = "/getLoCsByStatus/{status}" )
    private List<LoCApplicationState> getLoCsByStatus(@PathVariable String status)  {
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();

        List<StateAndRef<LoCApplicationState>> apps=response.stream()
                .filter(app -> status.equals(app.getState().getData().getLocStatus().toString())).collect(Collectors.toList());
        for(StateAndRef<LoCApplicationState> res : apps){
            results.add(res.getState().getData());
        }

        //  System.out.println(proxy.vaultQuery(LoCApplicationState.class).getTotalStatesAvailable());
        //   List<StateAndRef<LoCApplicationState>> states=proxy.vaultQuery(LoCApplicationState.class).component1();
        return results;
        //   return new ResponseEntity<List<StateAndRef<LoCApplicationState>>>(states, HttpStatus.OK);
    }

    @GetMapping(value = "/getLoCCountByStatus/{status}" )
    private int getLoCCountByStatus(@PathVariable String status)  {

        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();
        int i=0;
        i =response.stream()
                .filter(app -> status.equals(app.getState().getData().getLocStatus().toString())).collect(Collectors.toList()).size();

        return i;
    }

    /*@GetMapping(value = "/getAdvicedLoCs" )
    private List<LoCApplicationState> getAdvicedLoCs()  {
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();

        List<StateAndRef<LoCApplicationState>> apps=response.stream()
                .filter(app -> "ADVICED".equals(app.getState().getData().getLocStatus().toString())).collect(Collectors.toList());
        for(StateAndRef<LoCApplicationState> res : apps){
            results.add(res.getState().getData());
        }


        return results;

    }*/

    /*@GetMapping(value = "/getAcceptedLoCs" )
    private List<LoCApplicationState> getAcceptedLoCs()  {
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();

        List<StateAndRef<LoCApplicationState>> apps=response.stream()
                .filter(app -> "ACCEPTED".equals(app.getState().getData().getLocStatus().toString())).collect(Collectors.toList());
        for(StateAndRef<LoCApplicationState> res : apps){
            results.add(res.getState().getData());
        }


        return results;

    }
*/

    @GetMapping(value = "/getLoCApplicationById/{id}" )
    private LoCApplicationState getLoCApplicationById(@PathVariable String id, ModelAndView mav) throws NoSuchFieldException {
       /* System.out.println(proxy.vaultQuery(LoCState.class).getTotalStatesAvailable());
        List<StateAndRef<LoCState>> states=proxy.vaultQuery(LoCState.class).component1();*/
      /*  QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
        FieldInfo lenderField = QueryCriteriaUtils.getField("appId", LoCApplicationState.class);
        CriteriaExpression lenderIndex = Builder.equal(lenderField, id);
        QueryCriteria lenderCriteria = new QueryCriteria.VaultCustomQueryCriteria(lenderIndex);
        QueryCriteria criteria = generalCriteria.and(lenderCriteria);*/
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();

        StateAndRef<LoCApplicationState> apps=response.stream()
                .filter(app -> id.equals(app.getState().getData().getLocID().getId().toString())).findAny().orElse(null);
        if(null!= apps){
            //    mav.addObject("loc", apps.getState().getData());
            //    mav.setViewName("inlandlcissuance");
            return apps.getState().getData();
            //   return apps.getState().getData();
        }else{
            return null;
        }
        /*List<StateAndRef<LoCApplicationState>> results = proxy.vaultQueryByCriteria(criteria,LoCApplicationState.class).getStates();
    //   return results;


        return new ResponseEntity<List<StateAndRef<LoCApplicationState>>>(results, HttpStatus.OK);*/
    }

    @GetMapping(value = "/getAproveLocView" )
    private ModelAndView getAproveLocView(ModelAndView mav) throws NoSuchFieldException {
        mav.setViewName("inlandlcIssuance");
        return mav;
    }
    @GetMapping(value ="/getMyLoCs")
    private List<LoCApplicationState> getMyLoCs() throws NoSuchFieldException {
        // QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        //   Field applicant = LoCSchemaV1.PersistentLoC.class.getDeclaredField("applicant");
        //  CriteriaExpression lenderIndex = Builder.equal(applicant, me.getName().toString());
        //   QueryCriteria lenderCriteria = new QueryCriteria.VaultCustomQueryCriteria(lenderIndex);
        //  QueryCriteria criteria = generalCriteria.and(lenderCriteria);
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQuery(LoCApplicationState.class).getStates();
        for(StateAndRef<LoCApplicationState> res : response){
            results.add(res.getState().getData());
        }
        List<LoCApplicationState> finalResults =
                results.stream().filter(data -> me.getName().toString().equals(data.getApplicant().getName().toString())).collect(Collectors.toList());
        return finalResults;
    }
    @GetMapping(value ="/getLoCStatesByStatus/{status}")
    private List<LoCApplicationState> getLoCStatesByStatus(@PathVariable String status) throws NoSuchFieldException {
        // QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        //   Field applicant = LoCSchemaV1.PersistentLoC.class.getDeclaredField("applicant");
        //  CriteriaExpression lenderIndex = Builder.equal(applicant, me.getName().toString());
        //   QueryCriteria lenderCriteria = new QueryCriteria.VaultCustomQueryCriteria(lenderIndex);
        //  QueryCriteria criteria = generalCriteria.and(lenderCriteria);
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response;
        if(status.equalsIgnoreCase("CONSUMED")) {

            //   proxy.vaultQueryByCriteria(new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL), LoCApplicationState.class);
            response = proxy.vaultQueryByCriteria(new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.CONSUMED), LoCApplicationState.class).getStates();
        }else if(status.equalsIgnoreCase("UNCONSUMED")) {

            response = proxy.vaultQueryByCriteria(new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED), LoCApplicationState.class).getStates();
        }else if(status.equalsIgnoreCase("ALL")) {
            response = proxy.vaultQueryByCriteria(new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL), LoCApplicationState.class).getStates();
        }else{

            return null;
        }
        for(StateAndRef<LoCApplicationState> res : response){
            results.add(res.getState().getData());
        }
        // List<LoCApplicationState> finalResults =
        //        results.stream().filter(data -> me.getName().toString().equals(data.getApplicant().getName().toString())).collect(Collectors.toList());
        return results;
    }
    //  val consumedStates = rpcOps.vaultQueryBy<ContractState>(QueryCriteria.VaultQueryCriteria(Vault.StateStatus.CONSUMED)).states
    /*@GetMapping(value ="/getMyLoCsQueryable")
    private List<LoCApplicationState> getMyLoCsQueryable() throws NoSuchFieldException {
        QueryCriteria generalCriteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.ALL);
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Field applicant = LoCSchemaV1.PersistentLoC.class.getDeclaredField("applicant");
        CriteriaExpression lenderIndex = Builder.equal(applicant, me.getName().toString());
        QueryCriteria lenderCriteria = new QueryCriteria.VaultCustomQueryCriteria(lenderIndex);
        QueryCriteria criteria = generalCriteria.and(lenderCriteria);
        List<LoCApplicationState> results = new ArrayList<LoCApplicationState>();
        List<StateAndRef<LoCApplicationState>> response = proxy.vaultQueryByCriteria(criteria,LoCApplicationState.class).getStates();
        for(StateAndRef<LoCApplicationState> res : response){
            results.add(res.getState().getData());
        }
        return results;
    }*/

    @GetMapping(value = "/getNotificationDetailView" )
    private ModelAndView getNotificationDetailView(ModelAndView mav) throws NoSuchFieldException {
        mav.setViewName("notificationDetails");
        return mav;
    }

    @GetMapping(value = "/getAmendmentDetailsView" )
    private ModelAndView getAmendmentDetailsView(ModelAndView mav) throws NoSuchFieldException {
        mav.setViewName("amendmentDetails");
        return mav;
    }

    @PostMapping("/attachment")
    private ResponseEntity<String> uploadAttachment(@RequestParam MultipartFile file, @RequestParam String uploader) {
        String fileName=file.getOriginalFilename();
        if(null==fileName){
            return new ResponseEntity<String>("FileName shouldnt be null", HttpStatus.OK);
        }
    /*    if(file.getContentType()!="application/zip" || file.getContentType()!="application/jar"){

        }

        }else{*/
        try {
            SecureHash hash= proxy.uploadAttachmentWithMetadata(file.getInputStream(), uploader, fileName);
            return new ResponseEntity<String>(hash.toString(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }
        //  }
        //  return null;
    }
    @GetMapping("/download/{hash}")
    private ResponseEntity<Resource> downloadFile(@PathVariable String hash){
        InputStreamResource resource= new InputStreamResource(proxy.openAttachment(SecureHash.parse(hash)));
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+hash+".zip\"").body(resource);
    }

    @PostMapping(value="/approveLoCApplication")
    private ResponseEntity approveLoC(HttpServletRequest request, HttpServletResponse response, @RequestBody LoCApplicationModel application){
        String locStatus= "Approved";

        return null;
    }

    @PostMapping(value="/saveLoCApplication")
    private ResponseEntity<LoCApplicationEntity> saveAsDraft(HttpServletRequest request, @RequestBody LoCApplicationEntity application){
        return new ResponseEntity<LoCApplicationEntity>(loCApplicationSerivce.save(application), HttpStatus.OK);
        //  return null;
    }

    @GetMapping(value="/getSavedLoCs")
    private ResponseEntity<List<LoCApplicationEntity>> getSavedLoCs(HttpServletRequest request){
        return new ResponseEntity<List<LoCApplicationEntity>>(loCApplicationSerivce.findAll(),HttpStatus.OK);

    }
    @GetMapping(value="/getDraftedLoCs")
    private ResponseEntity<List<LoCApplicationEntity>> getDraftedLoCs(HttpServletRequest request){
        //  return new ResponseEntity<List<LoCApplicationEntity>>(loCApplicationSerivce.findByLocStatus("Draft"),HttpStatus.OK);
        return null;
    }


    @PostMapping(value="/createLoCApplication")
    private ResponseEntity<Response> createLoC(HttpServletRequest request, @RequestBody LoCApplicationEntity application){

        System.out.println(application.toString());
        double amount= application.getAmount();
        String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();
        String locStatus= "Open";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
        if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }
        if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }
        CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party beneficiary=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);

        if(beneficiary==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+beneficiary);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);

        try{
            //  LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            CordaFuture stx= proxy.startTrackedFlowDynamic(ApplyLoCFlow.class, me, beneficiary, issuingBank, advisingBank,
                    application.getApplicationCreationDate(), application.getLcType(), application.getApplicationRuleCode(), application.getAppRuleDesc(),
                    application.getApplicantID(), application.getApplicantAddress(), application.getCurrency(),
                    application.getAmount(), application.getCreditAmountTolerance(), application.getDebitAmountTolerance(),
                    application.getIssueDate(), application.getExpiryDate(), application.getExpiryPlace(),
                    application.getPresentationDays(), application.getShipmentDate(), application.getShipmentPeriod(),
                    application.getIncoTerms(), application.getPartialShipment(), application.getTranshipmentShipment(),
                    application.getPortOfLoading(), application.getPortOfDischarge(), application.getPlaceOfTakingInCharge(),
                    application.getFinalDestination(), application.getBeneficiaryName(), application.getBeneficiaryAddress(),
                    application.getIssuingBankName(), application.getIssuingBankAddress(), application.getAdvisingBankName(),
                    application.getAdvisingBankAddress(), application.getCreditAvailableBy(), application.getCreditAvailableWith(),
                    application.getBankName(), application.getBankAddress(), application.getIfscCode(),
                    application.getChargesFrom(), application.getChargesDefaultAccount(), application.getWaiveCharges(),
                    application.getChargeCodeOne(), application.getChargeDebitAccountNum(), application.getChargeCurrency(),
                    application.getChargeAmount(), application.getCommissionCode(), application.getWaiveCommissionFlag(),
                    application.getCommissionPartyCharge(), application.getCommissionCurrency(), application.getCommissionAmount(),
                    application.getCommissionAccount(), application.getMarginRequired(), application.getMarginCalcBase(),
                    application.getMarginPercentage(), application.getMarginDebitAccount(), application.getMarginAmount(),
                    application.getMarginCreditAccount(), application.getDraft(), application.getPoPiContractRegNum(),
                    application.getPoAmount(), application.getPoDate(), application.getDescOfGoods(),
                    application.getDocsRequired(), application.getAdditionalConditions(), Collections.emptyList()).getReturnValue();

            res.setStatus("Success");
            res.setResponse(stx.get().toString());
            System.out.println("Tx id: " + stx.get().toString());
            res.setLocId(stx.get().toString().substring(0, stx.get().toString().indexOf(";")));

            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping(value="/approveLoC/{id}")
    private ResponseEntity<Response> approveLoC(HttpServletRequest request, @PathVariable String id,
                                                @RequestBody LoCApplicationEntity application){

        System.out.println(application.toString());
        //    double amount= application.getAmount();
        String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();
        String applicant = application.getApplicant();

        //   application.setId(Integer.parseInt(application.getLcNumber().substring(5)));

        Response res = new Response();
        if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }
      /*  if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }*/

        CordaX500Name mine= CordaX500Name.parse(applicant);
        Party applicantParty=proxy.wellKnownPartyFromX500Name(mine);

        CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party beneficiary=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);

        if(beneficiary==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+beneficiary);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);

        try{

            CordaFuture stx= proxy.startTrackedFlowDynamic(ApproveLoCFlow.class, id, application.getLcNumber(), applicantParty, beneficiary, issuingBank, advisingBank,
                    application.getLcType(), application.getApplicationRuleCode(), application.getAppRuleDesc(),
                    application.getApplicantID(), application.getApplicantAddress(), application.getCurrency(),
                    application.getAmount(), application.getCreditAmountTolerance(), application.getDebitAmountTolerance(),
                    application.getIssueDate(), application.getExpiryDate(), application.getExpiryPlace(),
                    application.getPresentationDays(), application.getShipmentDate(), application.getShipmentPeriod(),
                    application.getIncoTerms(), application.getPartialShipment(), application.getTranshipmentShipment(),
                    application.getPortOfLoading(), application.getPortOfDischarge(), application.getPlaceOfTakingInCharge(),
                    application.getFinalDestination(), application.getBeneficiaryName(), application.getBeneficiaryAddress(),
                    application.getIssuingBankName(), application.getIssuingBankAddress(), application.getAdvisingBankName(),
                    application.getAdvisingBankAddress(), application.getCreditAvailableBy(), application.getCreditAvailableWith(),
                    application.getBankName(), application.getBankAddress(), application.getIfscCode(),
                    application.getChargesFrom(), application.getChargesDefaultAccount(), application.getWaiveCharges(),
                    application.getChargeCodeOne(), application.getChargeDebitAccountNum(), application.getChargeCurrency(),
                    application.getChargeAmount(), application.getCommissionCode(), application.getWaiveCommissionFlag(),
                    application.getCommissionPartyCharge(), application.getCommissionCurrency(), application.getCommissionAmount(),
                    application.getCommissionAccount(), application.getMarginRequired(), application.getMarginCalcBase(),
                    application.getMarginPercentage(), application.getMarginDebitAccount(), application.getMarginAmount(),
                    application.getMarginCreditAccount(), application.getDraft(), application.getPoPiContractRegNum(),
                    application.getPoAmount(), application.getPoDate(), application.getDescOfGoods(),
                    application.getDocsRequired(), application.getAdditionalConditions(), new CommentsState(null,null,null,null)).getReturnValue();


            LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            res.setStatus("Success");
            res.setResponse(stx.toString());
            res.setLocId(entity.getLcNumber());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping(value="/adviceLoC/{id}")
    private ResponseEntity<Response> adviceLoC(HttpServletRequest request,
                                               @PathVariable String id){


        Party beneficiary=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=PartyB,L=Hyderabad,C=IN"));
        Party issuingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=BankA,L=Mumbai,C=IN"));
        Party advisingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=BankB,L=Hyderabad,C=IN"));
       /* String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();*/
        //     String locStatus= "Approved";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
       /* if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }*/
      /*  if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }*/
        /*CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party party=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);*/
/*

        if(party==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+party);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);
*/

        try{

            CordaFuture stx= proxy.startTrackedFlowDynamic(AdviceLoCFlow.class, id).getReturnValue();
            //    LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            res.setStatus("Success");
            res.setResponse(stx.toString());
            //   res.setLocId(entity.getLocId());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value="/acceptLoC/{id}")
    private ResponseEntity<Response> acceptLoC(HttpServletRequest request,
                                               @PathVariable String id){


        Party beneficiary=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=PartyB,L=Hyderabad,C=IN"));
        Party issuingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=BankA,L=Mumbai,C=IN"));
        Party advisingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=BankB,L=Hyderabad,C=IN"));
       /* String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();*/
        //     String locStatus= "Approved";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
       /* if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }*/
      /*  if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }*/
        /*CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party party=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);*/
/*

        if(party==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+party);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);
*/

        try{

            CordaFuture stx= proxy.startTrackedFlowDynamic(AcceptLoCFlow.class, id).getReturnValue();
            //    LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            res.setStatus("Success");
            res.setResponse(stx.toString());
            //   res.setLocId(entity.getLocId());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value="/alterLoC/{id}")
    private ResponseEntity<Response> alterLoC(HttpServletRequest request,
                                              @PathVariable String id, @RequestBody CommentsEntity commentsEntity){


        Party beneficiary=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=EXPORTER KENT Company,L=Hyderabad,C=IN"));
        Party issuingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=TT Bank,L=Mumbai,C=IN"));
        Party advisingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=BKC Bank,L=Hyderabad,C=IN"));
        Party applicant=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=IMPORTER ABC Company,L=Mumbai,C=IN"));
       /* String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();*/
        //     String locStatus= "Approved";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
       /* if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }*/
      /*  if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }*/
        /*CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party party=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);*/
/*

        if(party==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+party);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);
*/

        try{

            CordaFuture stx= proxy.startTrackedFlowDynamic(AlterLoCFlow.class, id, me.getName().getOrganisation(),
                    applicant.getName().getOrganisation(), commentsEntity.getComment(),
                    commentsEntity.getDate()).getReturnValue();
            //    LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            res.setStatus("Success");
            res.setResponse(stx.toString());
            //   res.setLocId(entity.getLocId());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value="/amendLoC/{id}")
    private ResponseEntity<Response> amendLoC(HttpServletRequest request,
                                              @PathVariable String id, @RequestBody CommentsEntity commentsEntity){


        Party beneficiary=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=EXPORTER KENT Company,L=Hyderabad,C=IN"));
        Party issuingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=TT Bank,L=Mumbai,C=IN"));
        Party advisingBank=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=BKC Bank,L=Hyderabad,C=IN"));
        Party applicant=proxy.wellKnownPartyFromX500Name(CordaX500Name.parse("O=IMPORTER ABC Company,L=Mumbai,C=IN"));
       /* String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();*/
        //     String locStatus= "Approved";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
       /* if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }*/
      /*  if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }*/
        /*CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party party=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);*/
/*

        if(party==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+party);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);
*/

        try{

            CordaFuture stx= proxy.startTrackedFlowDynamic(AmendLoCFlow.class, id, me.getName().getOrganisation(),
                    applicant.getName().getOrganisation(), commentsEntity.getComment(),
                    commentsEntity.getDate()).getReturnValue();
            //    LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            res.setStatus("Success");
            res.setResponse(stx.toString());
            //   res.setLocId(entity.getLocId());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping(value="/applyAlteredLoCFlow/{id}")
    private ResponseEntity<Response> applyAlteredLoCFlow(HttpServletRequest request, @PathVariable String id, @RequestBody LoCApplicationEntity application){

        System.out.println(application.toString());
        double amount= application.getAmount();
        String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();
        //   String locStatus= "Open";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
        if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }
        if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }
        CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party beneficiary=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);

        if(beneficiary==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+beneficiary);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);

        try{
            //   LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            CordaFuture stx= proxy.startTrackedFlowDynamic(ApplyAlteredLoCFlow.class, id, application.getLcNumber(), me, beneficiary, issuingBank, advisingBank,
                    application.getApplicationCreationDate(), application.getLcType(), application.getApplicationRuleCode(), application.getAppRuleDesc(),
                    application.getApplicantID(), application.getApplicantAddress(), application.getCurrency(),
                    application.getAmount(), application.getCreditAmountTolerance(), application.getDebitAmountTolerance(),
                    application.getIssueDate(), application.getExpiryDate(), application.getExpiryPlace(),
                    application.getPresentationDays(), application.getShipmentDate(), application.getShipmentPeriod(),
                    application.getIncoTerms(), application.getPartialShipment(), application.getTranshipmentShipment(),
                    application.getPortOfLoading(), application.getPortOfDischarge(), application.getPlaceOfTakingInCharge(),
                    application.getFinalDestination(), application.getBeneficiaryName(), application.getBeneficiaryAddress(),
                    application.getIssuingBankName(), application.getIssuingBankAddress(), application.getAdvisingBankName(),
                    application.getAdvisingBankAddress(), application.getCreditAvailableBy(), application.getCreditAvailableWith(),
                    application.getBankName(), application.getBankAddress(), application.getIfscCode(),
                    application.getChargesFrom(), application.getChargesDefaultAccount(), application.getWaiveCharges(),
                    application.getChargeCodeOne(), application.getChargeDebitAccountNum(), application.getChargeCurrency(),
                    application.getChargeAmount(), application.getCommissionCode(), application.getWaiveCommissionFlag(),
                    application.getCommissionPartyCharge(), application.getCommissionCurrency(), application.getCommissionAmount(),
                    application.getCommissionAccount(), application.getMarginRequired(), application.getMarginCalcBase(),
                    application.getMarginPercentage(), application.getMarginDebitAccount(), application.getMarginAmount(),
                    application.getMarginCreditAccount(), application.getDraft(), application.getPoPiContractRegNum(),
                    application.getPoAmount(), application.getPoDate(), application.getDescOfGoods(),
                    application.getDocsRequired(), application.getAdditionalConditions(), Collections.emptyList()).getReturnValue();

            res.setStatus("Success");
            res.setResponse(stx.toString());
            //    res.setLocId(entity.getLcNumber());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping(value="/applyAmendedLoCFlow/{id}")
    private ResponseEntity<Response> applyAmendedLoCFlow(HttpServletRequest request, @PathVariable String id, @RequestBody LoCApplicationEntity application){

        System.out.println(application.toString());
        double amount= application.getAmount();
        String otherParty=application.getBeneficiary();
        String iBank=application.getIssuingBank();
        String aBank=application.getAdvisingBank();
        //   String locStatus= "Open";
        Party me = proxy.nodeInfo().getLegalIdentities().get(0);
        Response res = new Response();
        if(iBank==null){
            //  return ResponseEntity.badRequest().body("Issuing Bank cannot be null");
        }
        if(aBank==null){
            //  return ResponseEntity.badRequest().body("Advising Bank cannot be null");
        }
        if(amount<=0){
            //  return ResponseEntity.badRequest().body("Amount should be positive");
        }
        CordaX500Name partyName= CordaX500Name.parse(otherParty);
        Party beneficiary=proxy.wellKnownPartyFromX500Name(partyName);

        CordaX500Name ibank= CordaX500Name.parse(iBank);
        Party issuingBank=proxy.wellKnownPartyFromX500Name(ibank);

        CordaX500Name abank= CordaX500Name.parse(aBank);
        Party advisingBank=proxy.wellKnownPartyFromX500Name(abank);

        if(beneficiary==null){

            res.setStatus("Error");
            res.setError("Can not find the party of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(issuingBank==null){
            // return ResponseEntity.badRequest().body("Can not find the bank of name "+abank.toString());
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+abank.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        if(advisingBank==null){
            res.setStatus("Error");
            res.setError("Can not find the bank of name "+partyName.toString());
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
        System.out.println("Party: "+beneficiary);
        System.out.println("iBank: "+issuingBank);
        System.out.println("aBank: "+advisingBank);

        try{
            //   LoCApplicationEntity entity=loCApplicationSerivce.save(application);
            CordaFuture stx= proxy.startTrackedFlowDynamic(ApplyAmendedLoCFlow.class, id, application.getLcNumber(), me, beneficiary, issuingBank, advisingBank,
                    application.getApplicationCreationDate(), application.getLcType(), application.getApplicationRuleCode(), application.getAppRuleDesc(),
                    application.getApplicantID(), application.getApplicantAddress(), application.getCurrency(),
                    application.getAmount(), application.getCreditAmountTolerance(), application.getDebitAmountTolerance(),
                    application.getIssueDate(), application.getExpiryDate(), application.getExpiryPlace(),
                    application.getPresentationDays(), application.getShipmentDate(), application.getShipmentPeriod(),
                    application.getIncoTerms(), application.getPartialShipment(), application.getTranshipmentShipment(),
                    application.getPortOfLoading(), application.getPortOfDischarge(), application.getPlaceOfTakingInCharge(),
                    application.getFinalDestination(), application.getBeneficiaryName(), application.getBeneficiaryAddress(),
                    application.getIssuingBankName(), application.getIssuingBankAddress(), application.getAdvisingBankName(),
                    application.getAdvisingBankAddress(), application.getCreditAvailableBy(), application.getCreditAvailableWith(),
                    application.getBankName(), application.getBankAddress(), application.getIfscCode(),
                    application.getChargesFrom(), application.getChargesDefaultAccount(), application.getWaiveCharges(),
                    application.getChargeCodeOne(), application.getChargeDebitAccountNum(), application.getChargeCurrency(),
                    application.getChargeAmount(), application.getCommissionCode(), application.getWaiveCommissionFlag(),
                    application.getCommissionPartyCharge(), application.getCommissionCurrency(), application.getCommissionAmount(),
                    application.getCommissionAccount(), application.getMarginRequired(), application.getMarginCalcBase(),
                    application.getMarginPercentage(), application.getMarginDebitAccount(), application.getMarginAmount(),
                    application.getMarginCreditAccount(), application.getDraft(), application.getPoPiContractRegNum(),
                    application.getPoAmount(), application.getPoDate(), application.getDescOfGoods(),
                    application.getDocsRequired(), application.getAdditionalConditions(), Collections.emptyList()).getReturnValue();

            res.setStatus("Success");
            res.setResponse(stx.toString());
            //    res.setLocId(entity.getLcNumber());
            System.out.println("Tx id: " + stx.toString());
            return new ResponseEntity(res, HttpStatus.OK);
        }catch(Exception ex){
            final String msg = ex.getMessage();
            logger.error(ex.getMessage(), ex);
            res.setStatus("Failed");
            res.setError(msg);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);

        }

    }
    /*private void getNotification(){
        Instant time;
        proxy.vaultTrack(LoCApplicationState.class).getUpdates().toBlocking().subscribe(
                update -> update.getProduced().forEach(abc-> abc.notify())
        );
    }*/

    @GetMapping(value="/notifications")
    private String getNotification(){
        /*Instant time;
        proxy.vaultTrack(LoCApplicationState.class).getUpdates().toBlocking().subscribe(
                update -> update.getProduced().forEach(abc-> abc.notify())
        );*/

        final DataFeed<Vault.Page<LoCApplicationState>, Vault.Update<LoCApplicationState>> dataFeed = proxy.vaultTrack(LoCApplicationState.class);
        final Vault.Page<LoCApplicationState> snapshot = dataFeed.getSnapshot();
        final Observable<Vault.Update<LoCApplicationState>> updates = dataFeed.getUpdates();
        // Log the 'placed' IOUs and listen for new ones.
        List<LoCApplicationState> result= new ArrayList<LoCApplicationState>();
        //  snapshot.getStates().forEach(state-> System.out.println("States: "+ result.add(state.getState().getData())));
        updates.subscribe(update -> update.getProduced().forEach(state->System.out.println("Updated: "+ result.add(state.getState().getData()))));
        //  updates.toBlocking().subscribe(update -> update.getProduced().forEach(state->System.out.println("Updated: "+ result.add(state.getState().getData()))));
        System.out.println("here");
        return updates.toBlocking().first().getProduced().toString();
    }

}