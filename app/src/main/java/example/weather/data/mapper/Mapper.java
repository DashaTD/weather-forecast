package example.weather.data.mapper;

interface Mapper<E, D> {

    D mapFromEntity(E type);

    E mapToEntity(D type);
}
