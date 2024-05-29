package fun.tanc.selfclocking.control;

import cn.dev33.satoken.util.SaResult;
import fun.tanc.selfclocking.model.Schedule;
import fun.tanc.selfclocking.service.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class ScheduleControl {
    @Autowired
    ScheduleServiceImpl scheduleService;


    //添加日程
    @PostMapping(value = "/addSchedule")
    public SaResult addSchedule(@RequestBody Map<String,String> map)
    {
        String userName = map.get("username");
        String scheduleFiled = map.get("scheduleFiled");
        String date = map.get("date");
        Boolean b = scheduleService.addSchedule(userName, scheduleFiled, date);
        if (b){
            return SaResult.ok("添加成功").setData(true);
        }else {
            return SaResult.error("添加失败");
        }
    }

    //列出所有日程
    @GetMapping(value = "/findAllSchedule")
    public SaResult findAllSchedule(@RequestParam("username") String userName)
    {
        List<Schedule> allSchedule = scheduleService.findAllSchedule(userName);
        if (allSchedule != null) {
            return SaResult.data(allSchedule);
        }
        return SaResult.ok("此用户没有日程");
    }


    //查询日程
    @PostMapping(value = "/findSchedule")
    public SaResult findSchedule(@RequestBody Map<String,String> map)
    {
        String scheduleFiled = map.get("scheduleFiled");
        String userName = map.get("userName");
        Schedule schedule = scheduleService.findSchedule(scheduleFiled, userName);
        if (schedule != null) {
            return SaResult.data(schedule);
        }
        return SaResult.ok("此用户没有此日程");
    }
}
