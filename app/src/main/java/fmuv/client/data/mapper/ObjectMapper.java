package fmuv.client.data.mapper;

import java.util.List;

public abstract class ObjectMapper<DomainModel, Model> {

    public abstract DomainModel transform(Model model);

    public abstract List<DomainModel> transformList(List<Model> models);

}
