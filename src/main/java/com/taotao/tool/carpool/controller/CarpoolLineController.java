package com.taotao.tool.carpool.controller;

import com.taotao.tool.carpool.constant.ECarpoolLineType;
import com.taotao.tool.carpool.entity.CarpoolLineDetailResp;
import com.taotao.tool.carpool.entity.CarpoolLineListItem;
import com.taotao.tool.carpool.entity.CarpoolLineListQuery;
import com.taotao.tool.carpool.entity.PositionDTO;
import com.taotao.tool.carpool.model.CarpoolDriver;
import com.taotao.tool.carpool.model.CarpoolLine;
import com.taotao.tool.carpool.model.CarpoolUser;
import com.taotao.tool.carpool.other.CarpoolLoginApi;
import com.taotao.tool.carpool.service.ICarpoolDriverService;
import com.taotao.tool.carpool.service.ICarpoolLinePointService;
import com.taotao.tool.carpool.service.ICarpoolLineService;
import com.taotao.tool.carpool.service.ICarpoolUserService;
import com.taotao.tool.common.dto.ApiResult;
import com.taotao.tool.common.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author caojiantao
 * @since 2023-12-21
 */
@Controller
@RequestMapping("/carpool/line")
public class CarpoolLineController {

    @Autowired
    private ICarpoolLineService carpoolLineService;
    @Autowired
    private ICarpoolLinePointService carpoolLinePointService;
    @Autowired
    private ICarpoolUserService carpoolUserService;
    @Autowired
    private ICarpoolDriverService carpoolDriverService;

    @CarpoolLoginApi
    @PostMapping("/save")
    public ApiResult<Void> save(CarpoolLine line) {
        CarpoolLine oldLine = carpoolLineService.getLineByOpenid(line.getOpenid());
        if (Objects.isNull(oldLine)) {
            carpoolLineService.save(line);
        } else {
            carpoolLineService.updateLineByOpenid(line);
        }
        return ApiResult.success();
    }

    @GetMapping("/list")
    public ApiResult<List<CarpoolLineListItem>> list(@Validated CarpoolLineListQuery query) {
        List<CarpoolLineListItem> list = new ArrayList<>();
        List<CarpoolLine> lineList = carpoolLineService.list();
        List<String> openidList = lineList.stream().map(CarpoolLine::getOpenid).collect(Collectors.toList());
        Map<String, CarpoolUser> userMap = carpoolUserService.getUserMapByOpenidList(openidList);
        Map<String, CarpoolDriver> driverMap = carpoolDriverService.getDriverMapByOpenidList(openidList);
        for (CarpoolLine line : lineList) {
            CarpoolUser user = userMap.get(line.getOpenid());
            CarpoolDriver driver = driverMap.get(line.getOpenid());
            if (Objects.isNull(user) || Objects.isNull(driver)) {
                continue;
            }
            PositionDTO homePosition = JsonUtils.parse(line.getHomePosition(), PositionDTO.class);
            PositionDTO workPosition = JsonUtils.parse(line.getWorkPosition(), PositionDTO.class);
            if (Objects.nonNull(line.getWorkTime())) {
                // 上班
                CarpoolLineListItem.Driver itemDriver = new CarpoolLineListItem.Driver(user.getAvatar());
                CarpoolLineListItem.Line itemLine = new CarpoolLineListItem.Line(homePosition.getName(), workPosition.getName(), line.getWorkTime().toString());
                CarpoolLineListItem.Seat itemSeat = new CarpoolLineListItem.Seat(line.getPrice(), line.getRemark());
                CarpoolLineListItem item = CarpoolLineListItem.builder()
                        .type(ECarpoolLineType.WORK)
                        .driver(itemDriver)
                        .line(itemLine)
                        .seat(itemSeat)
                        .build();
                list.add(item);
            }
            if (Objects.nonNull(line.getHomeTime())) {
                // 回家
                CarpoolLineListItem.Driver itemDriver = new CarpoolLineListItem.Driver(user.getAvatar());
                CarpoolLineListItem.Line itemLine = new CarpoolLineListItem.Line(workPosition.getName(), homePosition.getName(), line.getHomeTime().toString());
                CarpoolLineListItem.Seat itemSeat = new CarpoolLineListItem.Seat(line.getPrice(), line.getRemark());
                CarpoolLineListItem item = CarpoolLineListItem.builder()
                        .type(ECarpoolLineType.WORK)
                        .driver(itemDriver)
                        .line(itemLine)
                        .seat(itemSeat)
                        .build();
                list.add(item);
            }
        }
        return ApiResult.success(list);
    }

    @GetMapping("/detail")
    public ApiResult<CarpoolLineDetailResp> detail(@NotEmpty String openid, @NotNull ECarpoolLineType lineType) {
        CarpoolLineDetailResp detail = new CarpoolLineDetailResp();
        CarpoolUser user = carpoolUserService.getUserByOpenid(openid);
        CarpoolDriver driver = carpoolDriverService.getDriverByOpenid(openid);
        CarpoolLineDetailResp.Driver detailDriver = new CarpoolLineDetailResp.Driver(user.getAvatar(), user.getNickname(), driver.getPhone());
        detail.setDriver(detailDriver);
        CarpoolLine line = carpoolLineService.getLineByOpenid(openid);
        CarpoolLineDetailResp.Line detailLine = new CarpoolLineDetailResp.Line();
        if (Objects.equals(ECarpoolLineType.WORK, lineType)) {
            detailLine.setTime(line.getWorkTime().toString());
            detailLine.setFrom(JsonUtils.parse(line.getHomePosition(), PositionDTO.class));
            detailLine.setTo(JsonUtils.parse(line.getWorkPosition(), PositionDTO.class));
            detailLine.setPathwayList(JsonUtils.parseList(line.getPathwayPosition(), PositionDTO.class));
            detail.setLine(detailLine);
        } else if (Objects.equals(ECarpoolLineType.HOME, lineType)) {
            detailLine.setTime(line.getHomeTime().toString());
            detailLine.setFrom(JsonUtils.parse(line.getWorkPosition(), PositionDTO.class));
            detailLine.setTo(JsonUtils.parse(line.getHomePosition(), PositionDTO.class));
            detailLine.setPathwayList(JsonUtils.parseList(line.getPathwayPosition(), PositionDTO.class));
            Collections.reverse(detailLine.getPathwayList());
            detail.setLine(detailLine);
        }
        CarpoolLineDetailResp.Seat detailSeat = new CarpoolLineDetailResp.Seat(line.getPrice(), line.getRemark());
        detail.setSeat(detailSeat);
        return ApiResult.success(detail);
    }
}
