var req;
var isIE;
var completeField;
var completeTable;
var autoRow;

function init() {
    	completeField = document.getElementById("searchId");
        completeTable = document.getElementById("completetable"); 
		autoRow = document.getElementById("auto-row");
    }

    function doCompletion() {
        var url = "autocomplete?action=complete&searchId=" + escape(searchId.value);
        req = initRequest();
		console.log("reached javascript");
        req.open("GET", url, true);
        req.onreadystatechange = callback;
        req.send(null);
    }

    function initRequest() {
        if (window.XMLHttpRequest) {
            if (navigator.userAgent.indexOf('MSIE') != -1) {
                isIE = true;
            }
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            isIE = true;
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function appendProduct(productName, productId) {
        var row;
        var cell;
        var linkElement;
        if (isIE) {
            completeTable.style.display = 'block';
            row = completeTable.insertRow(completeTable.rows.length);
            cell = row.insertCell(0);
        } else {
            completeTable.style.display = 'table';
            row = document.createElement("tr");
            cell = document.createElement("td");
            row.appendChild(cell);
            completeTable.appendChild(row);
        }
        cell.className = "popupCell";
        linkElement = document.createElement("a");
        linkElement.className = "popupItem";
        linkElement.setAttribute("href", "product?action=lookup&item=" + productId);
        linkElement.appendChild(document.createTextNode(productName));
        cell.appendChild(linkElement);
    }

    function parseMessages(req) {
        // no matches returned
        if (req == null) {
        	console.log("failed");
            return false;
        } else {
        	parser = new DOMParser();
        	xmlDoc = parser.parseFromString(req.responseText,"text/xml");
  
            var products = xmlDoc.getElementsByTagName("products")[0];
            if (products.childNodes.length > 0) {
                completeTable.setAttribute("bordercolor", "black");
                completeTable.setAttribute("border", "1");
                for (loop = 0; loop < products.childNodes.length; loop++) {
                    var product = products.childNodes[loop];
                    var productName = product.getElementsByTagName("productName")[0];
                    var productId = product.getElementsByTagName("id")[0];
                    appendProduct(productName.childNodes[0].nodeValue,
                        productId.childNodes[0].nodeValue);
                }
            }
        }
    }

    function callback() {
        clearTable();
        if (req.readyState == 4) {
            if (req.status == 200) {
                parseMessages(req);
            }
        }
    }

    function clearTable() {
        if (completeTable.getElementsByTagName("tr").length > 0) {
            completeTable.style.display = 'none';
            for (loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
                completeTable.removeChild(completeTable.childNodes[loop]);
            }
        }
    }