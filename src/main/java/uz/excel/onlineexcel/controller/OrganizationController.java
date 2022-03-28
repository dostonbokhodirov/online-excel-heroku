package uz.excel.onlineexcel.controller;

import org.springframework.web.bind.annotation.*;
import uz.excel.onlineexcel.controller.base.AbstractController;
import uz.excel.onlineexcel.dto.organization.OrganizationCreateDto;
import uz.excel.onlineexcel.dto.organization.OrganizationDto;
import uz.excel.onlineexcel.dto.organization.OrganizationUpdateDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.OrganizationService;

import java.util.List;

@RestController
@RequestMapping("/organization/")
public class OrganizationController extends AbstractController<OrganizationService> {

    public OrganizationController(OrganizationService service) {
        super(service);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<DataDto<OrganizationDto>> get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping(value = "create")
    public ResponseEntity<DataDto<Long>> create(@RequestBody OrganizationCreateDto dto) {
        return service.create(dto);
    }


    @PatchMapping(value = "update")
    public ResponseEntity<DataDto<Long>> update(@RequestBody OrganizationUpdateDto dto) {
        return service.update(dto);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable Long id) {
        return service.delete(id);
    }


    @GetMapping("list")
    public ResponseEntity<DataDto<List<OrganizationDto>>> getAll() {
        return service.getAll();
    }
}
