package com.delicacy.cookies.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linzhenghui
 * @date 2020/10/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemoEvent {

    private String eventName;

}
