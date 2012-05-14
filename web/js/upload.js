var dropZoneElement = document.getElementById("dropzone");

dropZoneElement.addEventListener('dragleave', onDragLeave, false);
dropZoneElement.addEventListener('dragenter', onDragEnter, false);
dropZoneElement.addEventListener('dragover', onDragOver, false);
dropZoneElement.addEventListener('drop', onDrop, false);

function onDragLeave(event) {
        event.preventDefault();
        event.stopPropagation();
        //you can remove a style from the drop zone

}

function onDragEnter(event) {
        event.preventDefault();
        event.stopPropagation();
        //you can add a style to the drop zone
}

function onDragOver(event) {
        event.preventDefault();
        event.stopPropagation();
        event.dataTransfer.effectAllowed= "copy";
        event.dataTransfer.dropEffect = "copy";
}

function onDrop(event) {
        event.preventDefault();
        event.stopPropagation();
        onFilesSelected(event.dataTransfer.files);
}

function onFilesSelected(files) {
	var img = null;
	var reader = null;
	var dropZoneElement = document.getElementById("dropzone");

	for (var i=0; i<files.length; i++) {

	reader = new FileReader();
	reader.onloadend = function (evt) {
	img = document.createElement("img");
	dropZoneElement.appendChild(img);
	img.src = evt.target.result;
	};

	reader.readAsDataURL(files.item(i));

	}
}

function uploadFile(file) {
        var xhr = new XMLHttpRequest();
        //on s'abonne à l'événement progress pour savoir où en est l'upload
        xhr.upload.addEventListener("progress",  onUploadProgress, false);
        xhr.open("POST", "/upload", true);

        // on s'abonne à tout changement de statut pour détecter
        // une erreur, ou la fin de l'upload
        xhr.onreadystatechange = onStateChange; 

        xhr.setRequestHeader("Content-Type", "multipart/form-data");
        xhr.setRequestHeader("X-File-Name", file.fileName);
        xhr.setRequestHeader("X-File-Size", file.fileSize);
        xhr.setRequestHeader("X-File-Type", file.type);

        xhr.send(file);
}

function onUploadProgress(event) {
         if (event.lengthComputable) {
                 yourProgressBar.style.width = (event.loaded / event.total) * 100 + "%";
         }
}