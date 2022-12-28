package service;


import dao.RoomDao;
import dto.CreateDto.CreateRoomDto;
import dto.RoomDto;
import entity.Enum.FloorEnum;
import entity.Enum.NumberRoomEnum;
import entity.Enum.RoomStatusEnum;
import mapper.CreateRoomMapper;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class RoomService {

    private static final RoomService INSTANCE = new RoomService();
    private final RoomDao roomDao = RoomDao.getInstance();

    private final CreateRoomMapper createRoomMapper = CreateRoomMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    public List<RoomDto> findAll() {
        return roomDao.findAll().stream()
                .map(room -> RoomDto.builder()
                        .id(room.getId())
                        .number(room.getNumber())
                        .floor(room.getFloor())
                        .dayPrice(room.getDayPrice())
                        .status(room.getStatus())
                        .image(room.getImage())
                        .build())
                .collect(toList());

    }

    public List<RoomDto> findById(int id) {
        return roomDao.findById(id).stream()
                .map(room -> RoomDto.builder()
                        .id(room.getId())
                        .number(room.getNumber())
                        .floor(room.getFloor())
                        .dayPrice(room.getDayPrice())
                        .status(room.getStatus())
                        .image(room.getImage())
                        .build())
                .collect(toList());

    }

//    public void save(NumberRoomEnum number, int quantityBed, int categoryRoom, FloorEnum floor, int dayPrice, RoomStatusEnum status) {
//        var qua = QuantityBed.builder()
//                .id(quantityBed)
//                .build();
//        var cat = CategoryRoom.builder()
//                .id(categoryRoom)
//                .build();
//        var room = Room.builder()
//                .number(number)
//                .quantityBed(qua)
//                .categoryRoom(cat)
//                .floor(floor)
//                .dayPrice(dayPrice)
//                .status(status)
//                .build();
//        roomDao.save(room);
//
//    }

    public boolean delete(int id) {
        return roomDao.delete(id);

    }

    public void update(int id, NumberRoomEnum number, int quantityBed, int categoryRoom, FloorEnum floor, int dayPrice, RoomStatusEnum status) {
        var roomHotel = roomDao.findById(id);
        roomHotel.ifPresent(room -> {
            room.setNumber(number);
            room.setFloor(floor);
            room.setDayPrice(dayPrice);
            room.setStatus(status);
            roomDao.update(room);
        });
    }

    public Integer create(CreateRoomDto createRoomDto) {
        var room = createRoomMapper.mapFrom(createRoomDto);
        try {
            imageService.upload(room.getImage(), createRoomDto.getImage().getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        roomDao.save(room);
        return room.getId();
    }

    public static RoomService getInstance() {
        return INSTANCE;
    }
}