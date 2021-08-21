var locStatusAlter = "";
var locIdAlter = "";
var  lcNumberAlter = "";

    $(document).ready(function(){

            $(".trade-main").show();
            $(".lc-from-response").hide();
            $(".lc-form-content").hide();
            $(".lc-app-list").hide();

            $('#lc-form').click(function(){
                $(".trade-main").hide();
                $(".lc-from-response").hide();
                $(".lc-app-list").hide();
                $(".lc-form-content").show();
                $("#saveAndDraft").show();
                $('.notification-locForm').hide();
                $('.notification-details').hide();
            });



            $('#lc-alteration').on('click', function (e) {
                $('.table-striped').hide();
                $('.notification-details').hide();
                $('#page-content-wrapper1').hide();
                $('#pills-trade-tab').click();
                getLoCNotificationByStatus("ALTERED");
                $('.notification-locForm').show();
            });

            $('#lcAmendment').on('click', function (e) {
                $('.table-striped').hide();
                $('.notification-details').hide();
                $('#page-content-wrapper1').hide();
                $('#pills-trade-tab').click();
                getLoCNotificationByStatus("AMEND");
                $('.notification-locForm').show();
            });

            $('.fa-sign-out').on('click', function (e) {
                $.ajax({
                    url: '/logout',
                    type: 'GET',
                    contentType: 'application/json',
                    async: false,
                    success: function( data){
                        sessionStorage.setItem("username", "null");
                        location.href= "/login";
                    },
                    error: function(e){
                        console.log( e );
                    }
                });
            });

            $('#agreeCheck').on('click', function (e) {
                var agreeCheck = $('input[name=agreeCheck]:checked').val();
                if(agreeCheck != undefined){
                    $(".locSubmit").removeAttr("disabled");
                }else{
                    $(".locSubmit").attr('disabled','disabled');
                }
            });

            $('#creditAvailableWith').on('change',function() {
                var caw = $(this).val();
                  if(caw == "Negotiation restricted to"){
                      $(".negotiationSelect").show();
                  }else{
                    $(".negotiationSelect").css("display","none");
                  }
            });

            $("#pills-trade-tab").click(function(){
               $(".trade-main").show()
               $(".lc-from-response").hide();
               $(".lc-form-content").hide();
               $(".lc-app-list").hide();
               $('.notification-locForm').hide();
               $('.notification-details').hide();
            });

            $(function () {
                  $("#lcIssueDate").datepicker();
                  $("#lcExpiryDate").datepicker();
                  $("#shipmentDate").datepicker();
                  $("#poDate").datepicker();
            });

            $("#lc-list").click(function(){
                var response ;

                $(".trade-main").hide();
                $(".lc-from-response").hide();
                $(".lc-form-content").hide();
                $(".lc-app-list").show();
                $(".app-list-tbody").remove();

                $.ajax({
                        url: '/getMyLoCs',
                        dataType: 'JSON',
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,

                        processData: false,

                        success: function( data){
                            console.log(data);
                            response= data;
                        },
                        error: function(e){
                            console.log( e );
                        }
                });
                    var trHTML = '<tbody class="app-list-tbody">';
                $.each(response, function (i, item) {
                    trHTML +=
                           '<tr><td>' + item.lcNumber +
                           '</td><td> Inland LC issuance'+
                           '</td><td>' + item.applicant.name.organisation +
                           '</td><td>' + item.beneficiary.name.organisation +
                           '</td><td>' + item.currency +
                           '</td><td>' + item.amount +
                           '</td><td>' + item.locStatus +
                           '</td><td><span class="fa fa-eye" onclick="showDetails(event)"><br/><p style="display:none;">' +item.locID.id+
                      //     '</td><td style="visibility:hidden;" id="uid">' + item.locID.id +
                           '</p></span></td></tr>';
                });
                $('#app-list-table').append(trHTML+'</tbody>');

            });

        window.setInterval(function(){
               /// call your function here
              notificationCount("ALTERED");
              notificationCount("AMEND");
              notificationCount("ADVICED");
        }, 6000);


    });
        function showNotificationDetails(status){

                $('.table-striped').hide();
                $('.notification-details').hide();
                $('#page-content-wrapper1').hide();
                $('#pills-trade-tab').click();
                getLoCNotificationByStatus(status);
                $('.notification-locForm').show();

        }


        function notificationCount(status){
            var i=0;
            var j = 0;
            var k = 0;
            $.ajax({
                        url: '/getLoCCountByStatus/'+status,
                    //    dataType: 'JSON',
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                    //    data: JSON.stringify(data),
                        processData: false,

                        success: function( data){
                        if(status == 'ALTERED'){
                            i=data;
                        }else if(status == 'AMEND'){
                            j=data;
                        }else if(status == 'ADVICED'){
                             k=data;
                         }

                         //   console.log(data);
                        },
                        error: function(e){
                            console.log( e );

                        }
                    });

                    if(status == 'ALTERED'){
                        $(".badgeinfo").addClass("sec");
                        $(".badgeinfo").addClass("sec");
                        $(".fa-layers-counter").text(i);
                        if(i == 0){
                            $(".badgeinfo").removeClass("sec");
                        }
                    }
                    if(status == 'AMEND'){
                        $(".badgeinfo").addClass("sec1");
                        $(".fa-layers-counter1").text(j);
                        if(j == 0){
                            $(".badgeinfo").removeClass("sec1");
                        }
                    }
                    if(status == 'ADVICED'){
                        $(".badgeinfo").addClass("sec2");
                        if(k == 0){
                            $(".badgeinfo").removeClass("sec2");
                        }
                    }

        }
        function showDetails(event){

            var id= $(event.target).text();
             $.ajax({
                    url: '/getLoCApplicationById/'+id,
                //    dataType: 'JSON',
                    type: 'GET',
                    contentType: 'application/json',
                    async: false,
                //    data: JSON.stringify(data),
                    processData: false,

                    success: function( data){
                       // console.log(data);
                    },
                    error: function(e){
                        console.log( e );

                    }
                });
        }
        function showFormResonse(dataObj, str){

            $('.lcSuccessMsg').empty();
            $(".lc-form-content").hide();

            if(str == "ALTERED"){
                 $('.lcSuccessMsg').append("Successfully altered and forwarded to issuing bank");
                 $('#lcModal').modal('show');
            }else{

                $('.lcSuccessMsg').append("LC application has been "+ str +" with ID: " + dataObj.locId);
                $('#lcModal').modal('show');
            }

        }
        function createLoCApplication(){

                var currentDate = new Date();
                var issueDate = $('#lcIssueDate').val();
                var expiryDate = $('#lcExpiryDate').val();
                var shipmentDate = $('#shipmentDate').val();
                var amount = $('#amount').val();
                var poAmount = $('#poAmount').val();
                var lcIssuePlace = $('#lcIssuePlace').val();
                var poPIContractIndent = $('#poPIContractIndent').val();
                var poDate = $('#poDate').val();
                var descofGoods = $('#descofGoods').val();
                var docsRequired = $('#docsRequired').val();

                $('.amountEmpty').remove();
                $('.lcIssuePlaceEmpty').remove();
                $('.poAmountEmpty').remove();
                $('.POPIContractIndentEmpty').remove();
                $('.poDateEmpty').remove();
                 $('.descofGoodsEmpty').remove();
                 $('.docsRequiredEmpty').remove();
                 $('.shipmentDateEmpty').remove();

                if(amount == ""){
                    $('#amount').after("<span class='amountEmpty text-danger'>LC amount should not be empty</span>");
                    $('#amount').focus();
                    return false;
                }

                 if(issueDate == ""){
                     $('#lcIssueDate').after("<span class='lcIssueDateEmpty text-danger'>IssueDate should not be empty</span>");
                     $('#lcIssueDate').focus();
                     return false;
                 }
                 if(expiryDate == ""){
                     $('#lcExpiryDate').after("<span class='lcExpiryDateEmpty text-danger'>ExpiryDate should not be empty</span>");
                     $('#lcExpiryDate').focus();
                     return false;
                 }
                 if(shipmentDate == ""){
                     $('#shipmentDate').after("<span class='shipmentDateEmpty text-danger'>ShipmentDate should not be empty</span>");
                     $('#shipmentDate').focus();
                     return false;
                 }

                if(lcIssuePlace == ""){
                    $('#lcIssuePlace').after("<span class='lcIssuePlaceEmpty text-danger'>ExpiryPlace should not be empty</span>");
                    $('#lcIssuePlace').focus();
                    return false;
                 }

                 if(poPIContractIndent == ""){
                      $('#poPIContractIndent').after("<span class='POPIContractIndentEmpty text-danger'>POPIContractIndent should not be empty</span>");
                      $('#poPIContractIndent').focus();
                      return false;
                  }

                 if(poAmount == ""){
                     $('#poAmount').after("<span class='poAmountEmpty text-danger'>POAmount should not be empty</span>");
                     $('#poAmount').focus();
                     return false;
                 }

                 if(poDate == ""){
                    $('#poDate').after("<span class='poDateEmpty text-danger'>PODate should not be empty</span>");
                    $('#poDate').focus();
                    return false;
                 }

                 if(descofGoods == ""){
                     $('#descofGoods').after("<span class='descofGoodsEmpty text-danger'>descofGoods should not be empty</span>");
                     $('#descofGoods').focus();
                     return false;
                  }

                  if(docsRequired == ""){
                       $('#docsRequired').after("<span class='docsRequiredEmpty text-danger'>docsRequired should not be empty</span>");
                       $('#docsRequired').focus();
                       return false;
                    }

                if(parseInt(amount) > parseInt(poAmount)){
                     alert("LC Amount should not be more than the PO Amount");
                     return false;
                }

                currentDate = currentDate.getMonth()+1 +"/"+currentDate.getDate()+"/"+currentDate.getFullYear();
                 var currentDate = new Date(currentDate);
                 var expiryDate = new Date(expiryDate);
                 var issueDate = new Date(issueDate);
                 var shipmentDate = new Date(shipmentDate);

                if(issueDate < currentDate ){
                    alert("issueDate greater than or equal to current date");
                    $('#lcIssueDate').focus();
                    return false;
                }
                if(expiryDate <= currentDate ){
                    alert("expiryDate greater than current date");
                    $('#lcExpiryDate').focus();
                    return false;
                }
                if(shipmentDate < issueDate || shipmentDate > expiryDate){
                    alert("shipmentDate should be greater than issueDate and less than expiryDate");
                    $('#shipmentDate').focus();
                    return false;
                }

                $(".lc-from-response").empty();
                var data=Object();
                data.beneficiary= "O=EXPORTER KENT Company,L=Hyderabad,C=IN";
                data.issuingBank= "O=TT Bank,L=Mumbai,C=IN";
                data.advisingBank= "O=BKC Bank,L=Hyderabad,C=IN";
                data.currency="INR";
                data.amount=$('#amount').val();
                data.locStatus="Open";
                data.debitAmountTolerance= $('#debitAmountTolerance').val();
                data.creditAmountTolerance=$('#creditAmountTolerance').val();
                data.issueDate = $('#lcIssueDate').val();
                data.expiryDate = $('#lcExpiryDate').val();
                data.expiryPlace = $('#lcIssuePlace').val();
                data.presentationDays = $('#presentationDays').val();
                data.shipmentDate = $('#shipmentDate').val();
                data.shipmentPeriod = $('#shipmentPeriod').val();
                data.incoTerms = $('#incoTerms').val();
                data.partialShipment = $('#partialShipment').val();
                data.transhipmentShipment = $("#transhipmentShipment").val();
                data.portOfLoading = $("#portofLoading").val();
                data.portOfDischarge = $("#portOfDischarge").val();
                data.placeOfTakingInCharge = $("#placeOfTakingInCharge").val();
                data.finalDestination=	 $("#finalDestination").val();
                data.creditAvailableBy= $("#creditAvailableBy").val();
                data.creditAvailableWith= $("#creditAvailableWith").val();
                data.applicationCreationDate="";
                data.lcType="";
                data.applicationRuleCode="";
                data.appRuleDesc="";
                data.applicantID="";
                data.applicantAddress="";
                data.beneficiaryName = $("#beneficiaryName").val();
                data.beneficiaryAddress = $("#beneficiaryAddress").val();
                data.issuingBankName = $("#issuingBankName").val();
                data.issuingBankAddress = $("#issuingBankAddress").val();
                data.advisingBankName = $("#advisingBankID").val();
                data.advisingBankAddress = $("#advisingBankAddress").val();
                data.bankName = $("#bankName").val();
                data.bankAddress = $("#bankAddress").val();
               data.ifscCode = $("#iFSCCode").val();
               //charges
               var chargeForm = $('input[name=chargesFormRadio]:checked').val();
               if(chargeForm != undefined){
                    data.chargesFrom = chargeForm;
               }else{
                data.chargesFrom = "";
               }
               data.chargesDefaultAccount = "";
               data.waiveCharges = "";
               data.chargeCodeOne = "";
               data.chargeDebitAccountNum = "";
               data.chargeCurrency = "";
               data.chargeAmount = "";
               //commission
               data.commissionCode = "";
               data.waiveCommissionFlag = "";
               var commPartyCharge = $('input[name=commissionPartyRadio]:checked').val();
               if(commPartyCharge != undefined){
                    data.commissionPartyCharge = commPartyCharge;
               }else{
                data.commissionPartyCharge = "";
               }

               data.commissionCurrency = "";
               data.commissionAmount = "";
               data.commissionAccount = "";
               //margin
               var marginReq = $('input[name=marginRequired]:checked').val()
               if(marginReq != undefined){
                    data.marginRequired = marginReq;
               }else{
                    data.marginRequired = "";
               }
               data.marginCalcBase = "";
               data.marginPercentage = $("#marginPercent").val();
               data.marginDebitAccount = $("#marginDebitAccount").val();
               data.marginAmount = $("#marginAmount").val();
               data.marginCreditAccount = $("#marginCreditAccount").val();
               //Documents & Conditions
               var draftVal = $('input[name=draftRadio]:checked').val();
               if(draftVal != undefined){
                data.draft = $('input[name=draftRadio]:checked').val();
               }else{
                    data.draft = "";
               }

               data.poPiContractRegNum = $("#poPIContractIndent").val();
               data.poAmount = $("#poAmount").val();
               data.poDate = $("#poDate").val();
               data.descOfGoods = $("#descofGoods").val();
               data.docsRequired = $("#docsRequired").val();
               data.additionalConditions = $("#addtlCondition").val();
                console.log(data);

            if(locStatusAlter == "ALTERED"){

                //data.lcNumber = lcNumberAlter;
                data.id= lcNumberAlter.substring(4);
                $.ajax({
                            url: '/applyAlteredLoCFlow/'+locIdAlter,
                            dataType: 'JSON',
                            type: 'POST',
                            contentType: 'application/json',
                            async: false,
                            data: JSON.stringify(data),
                            processData: false,

                            success: function( data){
                            console.log(data);
                            data.locId= lcNumberAlter;
                                showFormResonse(data, "ALTERED");
                            },
                            error: function(e){
                                console.log( e );

                            }
                        });
                locStatusAlter="";
                lcNumberAlter="";
                locIdAlter="";
            }else{
                    $.ajax({
                            url: '/createLoCApplication',
                            dataType: 'JSON',
                            type: 'POST',
                            contentType: 'application/json',
                            async: false,
                            data: JSON.stringify(data),
                            processData: false,

                            success: function( data){
                            console.log(data);
                                $('#create-lc-form')[0].reset();
                                showFormResonse(data, "created");

                            },
                            error: function(e){
                                console.log( e );

                            }
                        });

                   }
        }

        function saveLoCApplicationAsDraft(){

                $(".lc-from-response").empty();
                var data=Object();
                data.beneficiary= "O=EXPORTER KENT Company,L=Hyderabad,C=IN";
                data.issuingBank= "O=TT Bank,L=Mumbai,C=IN";
                data.advisingBank= "O=BKC Bank,L=Hyderabad,C=IN";
                data.currency="INR";
                data.amount=$('#amount').val();
                data.locStatus="Draft";
                data.debitAmountTolerance= $('#debitAmountTolerance').val();
                data.creditAmountTolerance=$('#creditAmountTolerance').val();
                data.issueDate=$('#lcIssueDate').val();
                data.expiryDate=$('#lcExpiryDate').val();
                data.expiryPlace=$('#lcIssuePlace').val();
                data.presentationDays=$('#presentationDays').val();
                data.shipmentDate=$('#shipmentDate').val();
                data.shipmentPeriod=$('#shipmentPeriod').val();
                data.incoTerms= $('#incoTerms').val();
                data.partialShipment= $('#partialShipment').val();
                data.transhipmentShipment=$("#transhipmentShipment").val();
                data.portOfLoading=	 $("#portofLoading").val();
                data.portOfDischarge=	 $("#portOfDischarge").val();
                data.placeOfTakingInCharge=	 $("#placeOfTakingInCharge").val();
                data.finalDestination=	 $("#finalDestination").val();
                data.creditAvailableBy= "";
                //$("#creditAvailableBy").val();
                data.creditAvailableWith= "";
                //$("#creditAvailableWith").val();
                console.log(data);

                $.ajax({
                        url: '/saveLoCApplication',
                        dataType: 'JSON',
                        type: 'POST',
                        contentType: 'application/json',
                        async: false,
                        data: JSON.stringify(data),
                        processData: false,

                        success: function( data){
                        console.log(data);

                            showFormResonse(data, "saved as draft");
                        },
                        error: function(e){
                            console.log( e );

                        }
                    });
        }

        function getLoCNotificationByStatus(status){

            $(".approve-locForm").hide();
              var response;
                $.ajax({
                        url: '/getLoCsByStatus/'+status,
                        dataType: 'JSON',
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                    //    data: JSON.stringify(data),
                    //    processData: false,

                        success: function( data){
                        console.log(data);
                        response=data;
                        },
                        error: function(e){
                            console.log( e );
                        }
                    });

                     var trHTML = '<div id="page-content-wrapper" style="display:contents;">'+
                     '<div class="container-fluid" style="padding: 15px">'+
                     '<div class="container" style="padding-top: 15px">';

                     $.each(response, function (i, item) {
                     var operationType="";
                     if(item.locStatus == "ALTERED"){
                         operationType= "Alter Inland LC"
                     }else if (item.locStatus == "ADVICED"){
                         operationType= "Advice Inland LC"
                     }else if (item.locStatus == "AMEND"){
                         operationType= "Inland LC Amendment"
                     }

                        trHTML +=
                                        '<div class="card"><div class="card-body">'+
                                                             '<p calss="card-text">Operation Type   :'+ operationType+
                                                             '</p><p class="card-text">Importer Name  :'+ item.applicant.name.organisation +
                                                             '</p><p class="card-text">Exporter Name  :'+ item.beneficiary.name.organisation +
                                                             '</p><p class="card-text">LC Number  :'+ item.lcNumber +
                                                             '</p><button class="btn btn-primary" style="margin-left:350px" onclick="notificationDetailsView(event)">View<p style="display:none;">' +item.locID.id+
                                                              '</p></button></div></div>';
                     });

                     trHTML= trHTML+ '</div></div></div>';
                     $(".notification-locForm").html(trHTML);
            }

        var responseData;
       function notificationDetailsView(event){

            $('.notification-locForm').hide();
            $('.notification-details').empty();

            var locID = $(event.target).text().substring(4);

            $('.notification-details').show();

                $.ajax({
                        url: '/getLoCApplicationById/'+ locID,
                        type: 'GET',
                        contentType: 'application/json',
                        async: false,
                        processData: false,

                        success: function(data){
                        console.log(data);
                        responseData=data;
                        },
                        error: function(e){
                            console.log( e );
                        }
                    });

                      $.ajax({
                              url: '/getNotificationDetailView',
                              type: 'GET',
                              contentType: 'application/json',
                              async: false,
                              processData: false,

                              success: function(data){
                                  console.log(data);
                                  if(responseData.locStatus == 'ALTERED'){

                                        var trHTML = '<div id="page-content-wrapper" style="display:contents;">'+
                                                 '<div class="container-fluid" style="padding: 15px">'+
                                                 '<div class="container" style="padding-top: 15px">';

                                                    trHTML +=
                                                        '<div class="card"><div class="card-body">'+
                                                             '<p calss="card-text">Comments   :<br>'+ responseData.comments[responseData.comments.length-1].comment+
                                                             '</p><button class="btn btn-primary" style="margin-left:350px" onclick="lcEditWithAlterDetails()">OK<p style="display:none;">' +responseData.locID+
                                                             '</p></button></div></div>';

                                                 trHTML= trHTML+ '</div></div></div>';
                                                 $(".notification-details").html(trHTML);

                                  }else if(responseData.locStatus == 'AMEND'){

                                         var trHTML = '<div id="page-content-wrapper" style="display:contents;">'+
                                                  '<div class="container-fluid" style="padding: 15px">'+
                                                  '<div class="container" style="padding-top: 15px">';

                                                     trHTML +=
                                                         '<div class="card"><div class="card-body">'+
                                                              '<p calss="card-text">Comments   :<br>'+ responseData.comments[responseData.comments.length-1].comment+
                                                              '</p><button class="btn btn-primary" style="margin-left:350px" onclick="amendmentDetails()">OK<p style="display:none;">' +responseData.locID+
                                                              '</p></button></div></div>';

                                                  trHTML= trHTML+ '</div></div></div>';
                                                  $(".notification-details").html(trHTML);

                                  }else{
                                          $(".notification-details").append(data);
                                          $(".acceptButton").show();
                                          $("#locID").val(locID);
                                          $("#lcNumber").text(responseData.lcNumber);
                                          $("span#currency").text(responseData.currency);
                                          $("span#amount").text(responseData.amount);
                                          $("span#draft").text(responseData.draft);
                                          $("#applicantID").text(responseData.applicantID);
                                          $("span#beneficiaryName").text(responseData.beneficiaryName);
                                          $("span#issuingBankName").text(responseData.issuingBankName);
                                          $("#applicationRuleCode").text(responseData.applicationRuleCode);
                                          $("#appRuleDesc").text(responseData.appRuleDesc);
                                          $("span#lcIssueDate").text(responseData.issueDate);
                                          $("span#lcExpiryDate").text(responseData.expiryDate);
                                          $("span#lcIssuePlace").text(responseData.expiryPlace);
                                          $("span#finalDestination").text(responseData.finalDestination);
                                          $("span#presentationDays").text(responseData.presentationDays);
                                          $("span#creditAvailableWith").text(responseData.creditAvailableWith);
                                          $("span#partialShipment").text(responseData.partialShipment);
                                          $("span#transhipmentShipment").text(responseData.transhipmentShipment);
                                          $("span#shipmentDate").text(responseData.shipmentDate);
                                          $("span#placeOfTakingInCharge").text(responseData.placeOfTakingInCharge);
                                          $("span#shipmentPeriod").text(responseData.shipmentPeriod);
                                          $("#descriptionofGoods").text(responseData.descOfGoods);
                                          $("#documentsRequired").text(responseData.docsRequired);
                                  }

                              },
                              error: function(e){
                                  console.log( e );
                              }
                          });

       }

      function lcEditWithAlterDetails(){

           console.log(responseData);
           locStatusAlter = responseData.locStatus;
           locIdAlter = responseData.locID.id;
           lcNumberAlter = responseData.lcNumber;

              $(".trade-main").hide();
              $(".lc-from-response").hide();
              $(".lc-app-list").hide();
              $(".lc-form-content").show();
              $("#amount").val(responseData.amount);
              $("#creditAmountTolerance").val(responseData.creditAmountTolerance);
              $("#debitAmountTolerance").val(responseData.debitAmountTolerance);
              $("#lcIssueDate").val(responseData.issueDate);
              $("#lcExpiryDate").val(responseData.expiryDate);
              $("#lcIssuePlace").val(responseData.expiryPlace);
              $("#presentationDays").val(responseData.presentationDays);
              $("#shipmentDate").val(responseData.shipmentDate);
              $("#shipmentPeriod").val(responseData.shipmentPeriod);
              $("#incoTerms").val(responseData.incoTerms);
              $("#partialShipment").val(responseData.partialShipment);
              $("#transhipmentShipment").val(responseData.transhipmentShipment);
              $("#portofLoading").val(responseData.portOfLoading);
              $("#portOfDischarge").val(responseData.portOfDischarge);
              $("#placeOfTakingInCharge").val(responseData.placeOfTakingInCharge);
              $("#finalDestination").val(responseData.finalDestination);
              $("#creditAvailableBy").val(responseData.creditAvailableBy);
              $("#creditAvailableWith").val(responseData.creditAvailableWith);

              var caw = responseData.creditAvailableWith;
                if(caw == "Negotiation restricted to"){
                    $(".negotiationSelect").show();
                }else{
                  $(".negotiationSelect").css("display","none");
                }

              $("#bankName").val(responseData.bankName);
              $("#bankAddress").val(responseData.bankAddress);
              $("#iFSCCode").val(responseData.ifscCode);
              $("#poPIContractIndent").val(responseData.poPiContractRegNum);
              $("#poAmount").val(responseData.poAmount);
              $("#poDate").val(responseData.poDate);
              if(responseData.draft =='At sight'){
                  $("#atSightRadio").attr('checked', 'checked');
              }
              if(responseData.draft =='At usage'){
                  $("#atUsageRadio").attr('checked', 'checked');
              }
              $("#marginPercent").val(responseData.marginPercentage);
              $("#marginDebitAccount").val(responseData.marginDebitAccount);
              $("#marginAmount").val(responseData.marginAmount);
              $("#marginCreditAccount").val(responseData.marginCreditAccount);
              if(responseData.chargesFrom =='None'){
                  $("#chargesNoneRadio").attr('checked', 'checked');
              }
              if(responseData.chargesFrom =='Opener'){
                  $("#chargesOpenerRadio").attr('checked', 'checked');
              }
              if(responseData.chargesFrom =='Beneficiary'){
                 $("#chargesBeneficiaryRadio").attr('checked', 'checked');
              }

              if(responseData.commissionPartyCharge =='None'){
                $("#commissionNoneRadio").attr('checked', 'checked');
              }
              if(responseData.commissionPartyCharge =='Opener'){
                  $("#commissionOpenerRadio").attr('checked', 'checked');
              }
              if(responseData.commissionPartyCharge =='Beneficiary'){
                 $("#commissionBeneficiaryRadio").attr('checked', 'checked');
              }
              if(responseData.marginRequired=='on'){
                   $("#marginRequired").attr('checked', 'checked');
              }
              $("#descofGoods").val(responseData.descOfGoods);
              $("#docsRequired").val(responseData.docsRequired);
              $("#addtlCondition").val(responseData.additionalConditions);

              $("#saveAndDraft").hide();
              $('.notification-locForm').hide();
              $('.notification-details').hide();
          }

    function processLoCApplication(event){

        $(".lc-from-response").empty();
            var locID = $("#locID").val();
            console.log(locID);
            $.ajax({
                      url: '/acceptLoC/'+ locID,
                      type: 'POST',
                      contentType: 'application/json',
                      async: false,
                      processData: false,

                      success: function(data){
                      console.log(data);
                      $('.lcSuccessMsg').empty();
                      if(data.status == 'Success'){
                          $(".notification-details").hide();
                          $('.lcSuccessMsg').append('<h3>Successfully accepted the LC</h3>');
                          $('#lcModal').modal('show');

                          event.preventDefault();

                        }else{
                             $(".lc-from-response").append('<h3>'+data.error+'</h3>');
                             $(".lc-from-response").show();
                        }

                      },
                      error: function(e){
                          console.log( e );
                      }
                  });

         $(".lc-from-response").show();
    }

      function amendmentDetails(){

            $.ajax({
                    url: '/getAmendmentDetailsView',
                    type: 'GET',
                    contentType: 'application/json',
                    async: false,
                    processData: false,

                    success: function(data){
                        console.log(data);
                        $(".notification-details").empty();
                        $(".notification-details").append(data);

                        $(".form-horizontal #beneficiaryName").val(responseData.beneficiaryName);
                        $(".form-horizontal #amendmentNo").val(responseData.amendmentNumber);
                        $(".form-horizontal #lcamount").val(responseData.amount);
                        $(".form-horizontal #issuedate").val(responseData.issueDate);
                        $(".form-horizontal #expirydate").val(responseData.expiryDate);
                        $(".form-horizontal #shipmentdate").val(responseData.shipmentDate);
                        $(".form-horizontal #portofLoading").val(responseData.portOfLoading);
                        $(".form-horizontal #portOfDischarge").val(responseData.portOfDischarge);
                        $(".form-horizontal #debitAmountTolerance").val(responseData.debitAmountTolerance);
                        $(".form-horizontal #creditAmountTolerance").val(responseData.creditAmountTolerance);
                    },
                    error: function(e){
                        console.log( e );
                    }
                });

            }

            function sendForAmendAlteration(){

              var data= Object();
              var alterDetails = $('#alterDetails').val();
              var locID = $('#locID').val();
              console.log(locID);
              console.log(alterDetails);

              data.comment = alterDetails;
              data.date = new Date().toString();
              console.log(data.date);
                 $.ajax({
                  url: '/amendLoC/'+ locID,
                  dataType: 'JSON',
                  type: 'POST',
                  contentType: 'application/json',
                  async: false,
                  data: JSON.stringify(data),
                  processData: false,

                  success: function(data){
                       console.log(data);
                       $('.lcSuccessMsg').empty();
                       $('.notification-details').hide();
                       $('.lcSuccessMsg').append('<h3>Successfully sent for amendment</h3>');
                       $('#lcModal').modal('show');

                        event.preventDefault();
                  },
                  error: function(e){
                      console.log( e );
                  }
              });


      }

      function applyAmendLC(event){

            console.log(responseData);
            var data= Object();
            data = responseData;

             var lcNumberAmend = responseData.lcNumber;
            data.id= lcNumberAmend.substring(4);
            data.beneficiary= "O=EXPORTER KENT Company,L=Hyderabad,C=IN";
            data.issuingBank= "O=TT Bank,L=Mumbai,C=IN";
            data.advisingBank= "O=BKC Bank,L=Hyderabad,C=IN";
            data.applicant = "O=IMPORTER ABC Company,L=Mumbai,C=IN";
            data.issueDate = $('#issuedate').val();
            data.amount = $('#lcamount').val();
            data.expiryDate = $('#expirydate').val();
            data.shipmentDate = $('#shipmentdate').val();
            data.portOfLoading = $('.form-horizontal #portofLoading').val();
            data.portOfDischarge = $('.form-horizontal #portOfDischarge').val();
            data.creditAmountTolerance = $('.form-horizontal #creditAmountTolerance').val();
            data.debitAmountTolerance = $('.form-horizontal #debitAmountTolerance').val();

            data.locID = data.locID.id;
             $.ajax({
                 url: '/applyAmendedLoCFlow/'+data.locID,
                 dataType: 'JSON',
                 type: 'POST',
                 contentType: 'application/json',
                 async: false,
                 data: JSON.stringify(data),
                 processData: false,

                 success: function( data){

                     data.locId= lcNumberAmend;
                     $('.lcSuccessMsg').empty();
                     $(".notification-details").hide();
                     $('.lcSuccessMsg').append("Successfully amended and forwarded to issuing bank");
                     $('#lcModal').modal('show');
                     event.preventDefault();
                 },
                 error: function(e){
                     console.log( e );

                 }
             });
      }