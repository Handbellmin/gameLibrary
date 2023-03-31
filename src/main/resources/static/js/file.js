function selectFile(files, fileList) {
    const file = files[0];
    var tag = "";
    const fileName = file.name;
    fileList.push(file)
    document.getElementById("files").value = "";
    var fileSize = getFileSize(file);
    getAddFileTag(fileName, fileSize, tag);

}
function dropFile(files, fileList) {
    if(files != null && files != undefined) {
        var tag = "";
        console.log(files);
        for (var file of files) {
            console.log(file[0]);
            fileList.push(file);
            var fileName = file.name;
            var fileSize = getFileSize(file);
        }
        getAddFileTag(fileName, fileSize, tag);
    }
}
function getFileSize(file) {
    var fileSize = file.size / 1024 / 1024;
    fileSize = fileSize < 1? fileSize.toFixed(3) : fileSize.toFixed(1);
    return fileSize;
}
function getAddFileTag(fileName, fileSize, tag) {
    tag +=
            "<div class='fileList'>" +
            "<span class='fileName'>"+ fileName + "</span>" +
            "<span class='fileSize'>"+ fileSize + " MB</span>" +
            "<span class='clear'></span>" +
            "</div>";
    document.querySelector(".fileDrop").insertAdjacentHTML("afterbegin",tag);
}