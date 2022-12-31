package com.fivet.buddy.controller;

import com.fivet.buddy.dto.ChatFilesDTO;
import com.fivet.buddy.dto.ChatMsgDTO;
import com.fivet.buddy.services.ChatFilesService;
import com.fivet.buddy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("/chatFile/")
public class ChatFilesController {

    @Autowired
    private ChatFilesService chatFilesService;

    @Value("${chat.save.path}")
    String chatPath;

    @ResponseBody
    @RequestMapping("insert")
    public Map<String, String> insertFiles(MultipartFile[] uploadfile, FileUtil util, ChatMsgDTO chatMsgDto, ChatFilesDTO chatFilesDto) throws Exception {

        List<ChatFilesDTO> chatFilesList = new ArrayList<>();
        for (MultipartFile file : uploadfile) {
            if (!file.isEmpty()) {
                String sysName = UUID.randomUUID().toString() + file.getOriginalFilename();
                chatFilesDto = new ChatFilesDTO(
                        0,
                        file.getOriginalFilename(),
                        sysName,
                        chatMsgDto.getChatRoomSeq());
                chatFilesList.add(chatFilesDto);
                util.saves(uploadfile, chatPath, sysName);
            }
            chatFilesService.insertFiles(chatFilesDto);
        }
        Map<String, String> map = new HashMap<>();
        map.put("chatOriName",chatFilesDto.getChatOriName());
        map.put("chatSysName",chatFilesDto.getChatSysName());
        return map;
    }
    @RequestMapping("download")
    public ResponseEntity<Resource> download(FileUtil util, String chatSysName, String chatOriName) throws Exception{
        return util.download(chatPath,chatSysName,chatOriName);
    }
}
