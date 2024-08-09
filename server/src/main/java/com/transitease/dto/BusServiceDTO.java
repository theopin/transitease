package com.transitease.dto;

import lombok.*;

@Value
@Getter
@Setter
@AllArgsConstructor
public class BusServiceDTO {
	String ServiceNo;
	String Operator;
	int Direction;
	String Category;
	String OriginCode;
	String DestinationCode;
	String AM_Peak_Freq;
	String AM_Offpeak_Freq;
	String PM_Peak_Freq;
	String PM_Offpeak_Freq;
	String LoopDesc;
}
