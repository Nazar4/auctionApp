package nulp.pz.auction.service.mapper;

public interface Mapper<T, S> {

    T toEntity(final S s);
}
