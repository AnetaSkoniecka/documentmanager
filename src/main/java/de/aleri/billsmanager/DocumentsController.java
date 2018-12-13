package de.aleri.billsmanager;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DocumentsController {

    private DocumentRepository documentRepository;

    @Autowired
    public DocumentsController(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("/")
    public String documentsOverview(Model model) throws IOException {

        List<DocumentData> documents = documentRepository.findAll().stream().map(
            documentModel -> getDocumentData(documentModel)
        ).collect(Collectors.toList());

        model.addAttribute("documents", documents);

        return "documentsOverview";
    }

    private DocumentData getDocumentData(DocumentModel documentModel) {
        return DocumentData.builder()
            .id(documentModel.getId())
            .fileName(documentModel.getFileName())
            .downloadLink(MvcUriComponentsBuilder.fromMethodName(DocumentsController.class,
                    "downloadDocument", documentModel.getId().toString()).build().toString())
            .entranceDate(documentModel.getEntranceDate())
            .entrancePerson(documentModel.getEntrancePerson())
            .approvalDate(documentModel.getApprovalDate())
            .approvalPerson1(documentModel.getApprovalPerson1())
            .approvalPerson2(documentModel.getApprovalPerson2())
            .shippmentDate(documentModel.getShippmentDate())
            .comment(documentModel.getComment())
            .build();
    }

    @GetMapping("/files/{id:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadDocument(@PathVariable Long id) {

        Optional<DocumentModel> documentOptional = documentRepository.findById(id);
        if (documentOptional.isPresent()) {
            DocumentModel documentModel = documentOptional.get();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(documentModel.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentModel.getFileName() + "\"")
                    .body(new ByteArrayResource(documentModel.getFileData()));
        } else {
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws Exception {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        DocumentModel documentModel = new DocumentModel(fileName, file.getContentType(), file.getBytes());
        documentRepository.save(documentModel);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @GetMapping("/document/{id:.+}")
    public String documentEdit(@PathVariable Long id, Model model){

        Optional<DocumentModel> documentModel_ = documentRepository.findById(id);
        if (documentModel_.isPresent()) {
            DocumentModel documentModel = documentModel_.get();
            model.addAttribute("documents", getDocumentData(documentModel));
        }

        return "documentEdit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute DocumentData documentData) {

        Optional<DocumentModel> documentModel_ = documentRepository.findById(documentData.getId());
        if (documentModel_.isPresent()) {
            DocumentModel documentModel = documentModel_.get();

            documentModel.setEntranceDate(documentData.getEntranceDate());
            documentModel.setEntrancePerson(documentData.getEntrancePerson());
            documentModel.setApprovalDate(documentData.getApprovalDate());
            documentModel.setApprovalPerson1(documentData.getApprovalPerson1());
            documentModel.setApprovalPerson2(documentData.getApprovalPerson2());
            documentModel.setShippmentDate(documentData.getShippmentDate());
            documentModel.setComment(documentData.getComment());

            documentRepository.save(documentModel);
        }

        return "redirect:/";
    }

}
