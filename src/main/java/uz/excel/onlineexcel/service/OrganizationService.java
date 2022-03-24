package uz.excel.onlineexcel.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.excel.onlineexcel.dto.organization.OrganizationCreateDto;
import uz.excel.onlineexcel.dto.organization.OrganizationDto;
import uz.excel.onlineexcel.dto.organization.OrganizationUpdateDto;
import uz.excel.onlineexcel.entity.Organization;
import uz.excel.onlineexcel.mapper.OrganizationMapper;
import uz.excel.onlineexcel.repository.OrganizationRepository;
import uz.excel.onlineexcel.response.AppErrorDto;
import uz.excel.onlineexcel.response.DataDto;
import uz.excel.onlineexcel.response.ResponseEntity;
import uz.excel.onlineexcel.service.base.AbstractService;
import uz.excel.onlineexcel.service.base.BaseService;
import uz.excel.onlineexcel.service.base.GenericCrudService;
import uz.excel.onlineexcel.service.base.GenericService;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService
        extends AbstractService<OrganizationMapper, OrganizationRepository>
        implements GenericCrudService<OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto>,
        GenericService<OrganizationDto>, BaseService {

    public OrganizationService(OrganizationMapper mapper, OrganizationRepository repository) {
        super(mapper, repository);
    }

    @Override
    public ResponseEntity<DataDto<OrganizationDto>> get(Long id) {
        Optional<Organization> organization = repository.findById(id);
        if (organization.isPresent()) {
            OrganizationDto organizationDto = mapper.toDto(organization.get());
            return new ResponseEntity<>(new DataDto<>(organizationDto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto
                    .builder()
                    .message("ORGANIZATION_NOT_FOUND")
                    .status(HttpStatus.NOT_FOUND)
                    .build()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Long>> create(OrganizationCreateDto dto) {
        Organization organization = mapper.fromCreateDto(dto);
        Organization save = repository.save(organization);
        return new ResponseEntity<>(new DataDto<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDto<Long>> update(OrganizationUpdateDto dto) {
        Organization organization = mapper.fromUpdateDto(dto);
        Organization save = repository.save(organization);
        return new ResponseEntity<>(new DataDto<>(save.getId()));
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>(new DataDto<>(true));
    }

    @Override
    public ResponseEntity<DataDto<List<OrganizationDto>>> getAll() {
        List<Organization> organizationList = repository.findAll();
        List<OrganizationDto> organizationDtoList = mapper.toDto(organizationList);
        return new ResponseEntity<>(new DataDto<>(organizationDtoList));
    }


}
