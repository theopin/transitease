export interface BusService {
  ServiceNo: string;
  Operator: string;
  Direction: number;
  Category: string;
  OriginCode: string;
  DestinationCode: string;
  AM_Peak_Freq: string;
  AM_Offpeak_Freq: string;
  PM_Peak_Freq: string;
  PM_Offpeak_Freq: string;
  LoopDesc: string;
}

export interface NextBusInfo {
  OriginCode: string;
  DestinationCode: string;
  EstimatedArrival: string; // Consider using Date if parsing is needed
  Latitude: string;
  Longitude: string;
  Load: keyof typeof BusLoad;
  Type: keyof typeof BusType;
  Feature: keyof typeof PublicTransportFeature;
  VisitNumber: string;
}

export interface BusArrivalInfo {
  NextBus: NextBusInfo;
  NextBus2: NextBusInfo;
  NextBus3: NextBusInfo;
  Operator: keyof typeof PublicTransportOperator;
  ServiceNo: string;
}

export interface BusStopInfo {
  BusStopCode: string;
  Description: string;
  Latitude: number;
  Longitude: number;
  RoadName: string;
}

export enum PublicTransportOperator {
  SBST = 'SBS Transit',
  SMRT = 'SMRT Corporation',
  TTS = 'Tower Transit Singapore',
  GAS = 'Go Ahead Singapore',
}

export enum PublicTransportFeature {
  WAB = 'Wheelchair Accessible',
}

export enum BusLoad {
  SEA = 'Seats Available',
  SDA = 'Standing Available',
  LSD = 'Limited Standing',
  FB = 'Full Bus',
  UNKNOWN = 'Unknown',
}

export enum BusType {
  SD = 'Single Deck',
  DD = 'Double Deck',
  BD = 'Bendy',
  UNKNOWN = 'Unknown',
}