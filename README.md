# stcp_api ![CI](https://github.com/SergioCardoso7/stcp_api/actions/workflows/maven.yml/badge.svg)
A robust STCP (Sociedade de Transportes Colectivos do Porto) API.

## Description

Since STCP does not provide a public API, this project aims to provide a robust (unofficial) API built in JAVA using the Spring Boot framework to access STCP data.

This project aimed to be a simple demonstration of how to create a RESTful API using Spring Boot that is easy to use and understand.

**With this API, you can get the following data**: all the bus lines, all the bus stops of a specific line, information data about any bus stop and the most interesting feature: the next busses to arrive at a specific bus stop in real time.

## How to build and run the project

### How to build 

To build the project, ensure you have Maven installed and configured correctly on your system.

It is also important to have JAVA_HOME and MAVEN_HOME environment variables set up correctly.

(How to: https://medium.com/beelabacademy/configurando-variáveis-de-ambiente-java-home-e-maven-home-no-windows-e-unix-d9461f783c26)

- Windows
```bash
.\build-all.bat 
```

### How to run
```cmd
java -jar target\stcp_api-0.0.1-SNAPSHOT.jar
```

## Features

### Get information about all the bus lines

#### How to:

```http
http://localhost:8080/stcp_api/busline/getbuslines
```
#### Result:

```json
[
  {
    "lineCode": "200",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "CAST. QUEIJO"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "BOLHÃO"
    }
  },
  {
    "lineCode": "201",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "VISO"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "ALIADOS"
    }
  },
  {
    "lineCode": "202",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "PASSEIO ALEGRE (VIA AV. BESSA)"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "ALIADOS"
    }
  },
  {
    "lineCode": "203",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "CAST.QUEIJO"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "MARQUÊS"
    }
  },
  {
    "lineCode": "204",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "FOZ"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "HOSPITAL DE S.JOÃO"
    }
  },
  {
    "lineCode": "205",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "CASTELO DO QUEIJO"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "CAMPANHÃ"
    }
  },
  {
    "lineCode": "206",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "VISO"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "CAMPANHÃ"
    }
  },
  {
    "lineCode": "207",
    "directionOne": {
      "directionCode": 0,
      "directionEndStop": "MERCADO DA FOZ"
    },
    "directionTwo": {
      "directionCode": 1,
      "directionEndStop": "CAMPANHÃ"
    }
  }
  and more ...
]
```

### Get all the bus stops of a specific line

#### How to:

```http
http://localhost:8080/stcp_api/busline/linestops?lineCode={lineCode}&directionCode={directionCode}
```
#### Example:

```http
http://localhost:8080/stcp_api/busline/linestops?lineCode=207&directionCode=0
```
#### Result:

```json
[
  {
    "code": "CMP1",
    "name": "CAMPANHÃ",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.585913720929653,
      "y": 41.14974097400977
    }
  },
  {
    "code": "PBSS2",
    "name": "PINTO BESSA",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.58826867434113,
      "y": 41.14895364010724
    }
  },
  {
    "code": "CSS3",
    "name": "CAPELA S.RA SAÚDE",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.588961783819233,
      "y": 41.14752217321997
    }
  },
  {
    "code": "HER2",
    "name": "HEROISMO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.592919644535076,
      "y": 41.14653715260371
    }
  },
  {
    "code": "PRDR1",
    "name": "PRADO REPOUSO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.59670071289212,
      "y": 41.1460902775411
    }
  },
  {
    "code": "SLZ3",
    "name": "S. LÁZARO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.601288622222384,
      "y": 41.145828330007745
    }
  },
  {
    "code": "DQL1",
    "name": "DUQUE LOULÉ",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.603414536188742,
      "y": 41.14558582433168
    }
  },
  {
    "code": "ALXH3",
    "name": "ALEX. HERCULANO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.605230064787579,
      "y": 41.14426059012618
    }
  },
  {
    "code": "BTLH1",
    "name": "BATALHA",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.607252830729651,
      "y": 41.14524631370835
    }
  },
  {
    "code": "PRDJ",
    "name": "PR. D. JOÃO I",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.609073542321921,
      "y": 41.14758012783171
    }
  },
  {
    "code": "PRFL",
    "name": "PR.FILIPA DE LENCASTRE",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.61271521226057,
      "y": 41.148276273012655
    }
  },
  {
    "code": "GGF",
    "name": "GUIL. G. FERNANDES",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.61472509361306,
      "y": 41.1475906348156
    }
  },
  {
    "code": "CMO",
    "name": "CARMO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.616618399861883,
      "y": 41.147266168946274
    }
  },
  {
    "code": "HSA5",
    "name": "HOSP. ST. ANTÓNIO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.622404305358152,
      "y": 41.147793552105085
    }
  },
  {
    "code": "PAL2",
    "name": "PALÁCIO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.625401225040283,
      "y": 41.14917245237339
    }
  },
  {
    "code": "PRG4",
    "name": "PR. DA GALIZA",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.626931874767811,
      "y": 41.152530335126194
    }
  },
  {
    "code": "JM1",
    "name": "JUNTA MASSARELOS",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.631200244577744,
      "y": 41.1526976087124
    }
  },
  {
    "code": "GGT1",
    "name": "GÓLGOTA",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.633648041369149,
      "y": 41.15264587640078
    }
  },
  {
    "code": "PLNT1",
    "name": "PLANETÁRIO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.638286588959827,
      "y": 41.15311662787492
    }
  },
  {
    "code": "FCUP1",
    "name": "FACULDADE DE CIÊNCIAS",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.641293872583717,
      "y": 41.15384436761349
    }
  },
  {
    "code": "JB1",
    "name": "JARDIM BOTÂNICO",
    "zone": "PRT1",
    "coordinates": {
      "x": -8.644169764563308,
      "y": 41.154553450527615
    }
  },
  {
    "code": "LRD1",
    "name": "LORDELO",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.648969645229693,
      "y": 41.1544239552967
    }
  },
  {
    "code": "ALX1",
    "name": "ALEIXO",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.649408514158935,
      "y": 41.15260734401083
    }
  },
  {
    "code": "STC1",
    "name": "S.TA CATARINA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.650946644640168,
      "y": 41.15073218917036
    }
  },
  {
    "code": "FLU4",
    "name": "FLUVIAL",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.653396077398446,
      "y": 41.14882218023217
    }
  },
  {
    "code": "PGM1",
    "name": "PAULO DA GAMA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.657124213440083,
      "y": 41.14821648747471
    }
  },
  {
    "code": "PJUV1",
    "name": "POUSADA JUVENTUDE",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.659909184858375,
      "y": 41.14868636019974
    }
  },
  {
    "code": "PT3",
    "name": "PASTELEIRA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.66147264399848,
      "y": 41.15051729677504
    }
  },
  {
    "code": "CMPT1",
    "name": "CAMPO PASTELEIRA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.66217505383054,
      "y": 41.15255723353365
    }
  },
  {
    "code": "INSA1",
    "name": "IGREJA N.SRA DA AJUDA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.659385552602258,
      "y": 41.15320027099769
    }
  },
  {
    "code": "BPTL1",
    "name": "BR. DA PASTELEIRA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.658138320337747,
      "y": 41.15510068822269
    }
  },
  {
    "code": "DJ32",
    "name": "D.JOÃO III",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.660245068437403,
      "y": 41.157609293249145
    }
  },
  {
    "code": "SRVM5",
    "name": "SERRALVES (MUSEU)",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.659989255145682,
      "y": 41.1595495145825
    }
  },
  {
    "code": "JBR1",
    "name": "JOÃO BARROS",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.664154783555091,
      "y": 41.15869672025837
    }
  },
  {
    "code": "PVC1",
    "name": "PÊRO VAZ CAMINHA",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.66784056918391,
      "y": 41.15725055492902
    }
  },
  {
    "code": "PRI1",
    "name": "PR. DO IMPÉRIO",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.671291012329903,
      "y": 41.15588099854905
    }
  },
  {
    "code": "MFZ1",
    "name": "MERCADO DA FOZ",
    "zone": "PRT2",
    "coordinates": {
      "x": -8.673257215392086,
      "y": 41.155134995715656
    }
  }
]
```

### Get information data about any bus stop

#### How to:

```http
http://localhost:8080/stcp_api/busstop/stopdata?stopCode={stopCode}
```

#### Example:

```http
http://localhost:8080/stcp_api/busstop/stopdata?stopCode=1AL2
```

#### Result:

```json
{
  "code": "1AL2",
  "name": "ALAMEDA",
  "zone": "PRT1",
  "coordinates": {
    "x": -8.605230064787579,
    "y": 41.14426059012618
  }
}
```

### Get the next busses to arrive at a specific bus stop in real time

#### How to:

```http
http://localhost:8080/stcp_api/busstop/stoprealtimes?stopCode={stopCode}
```

#### Example:

```http
http://localhost:8080/stcp_api/busstop/stoprealtimes?stopCode=1AL2
```

#### Result:
```json 
[
  {
    "busLineCode": "200",
    "endBusStopName": "BOLHÃO -",
    "estimatedTimeOfArrival": "22:29:00",
    "waitingTime": "4min"
  },
  {
    "busLineCode": "501",
    "endBusStopName": "TRINDADE - P",
    "estimatedTimeOfArrival": "22:48:00",
    "waitingTime": "24min"
  },
  {
    "busLineCode": "200",
    "endBusStopName": "BOLHÃO -",
    "estimatedTimeOfArrival": "23:12:00",
    "waitingTime": "47min"
  }
]
```
