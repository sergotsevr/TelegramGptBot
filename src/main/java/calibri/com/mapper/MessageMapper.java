package calibri.com.mapper;

import calibri.com.dto.Message;
import calibri.com.entity.GptMessages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class MessageMapper {
//    @Mapping(target = "role", source = "message.role")
//    @Mapping(target = "content", source = "message.content")
//    public abstract GptMessages dataToEntity(Choice data);

//    @Mapping(target = "role", source = "role")
//    @Mapping(target = "content", source = "content")
    public abstract GptMessages dataToEntity(Message data);

   // public abstract Choice entityToData(GptMessages entity);

//    @Mapping(target = "role", source = "role")
//    @Mapping(target = "content", source = "content")
    public abstract Message entityToMessage(GptMessages entity);
}
