# geo-weather-service

POST http://localhost:8080/secure/geo-info
{
  "zipCode": "10010"
}

{
    "zip_code": "10010",
    "lat": 40.739077,
    "lng": -73.982601,
    "city": "New York",
    "state": "NY",
    "timezone": {
        "timezone_identifier": "America/New_York",
        "timezone_abbr": "EST",
        "utc_offset_sec": -18000,
        "is_dst": "F"
    },
    "acceptable_city_names": [
        {
            "city": "Madison Square Sta",
            "state": "NY"
        },
        {
            "city": "Manhattan",
            "state": "NY"
        },
        {
            "city": "Nyc",
            "state": "NY"
        }
    ],
    "area_codes": [
        212,
        646,
        917
    ]
}

GET http://localhost:8080/secure/weather?cityCode=10001

{
    "location": {
        "name": "Manhattan",
        "region": "New York",
        "country": "USA United States of America",
        "lat": 40.75,
        "lon": -73.99,
        "tz_id": "America/New_York",
        "localtime_epoch": 1609884409,
        "localtime": "2021-01-05 17:06"
    },
    "current": {
        "last_updated_epoch": 1609883120,
        "last_updated": "2021-01-05 16:45",
        "temp_c": 4.4,
        "temp_f": 39.9,
        "is_day": 0,
        "condition": {
            "text": "Overcast",
            "icon": "//cdn.weatherapi.com/weather/64x64/night/122.png",
            "code": 1009
        },
        "wind_mph": 0.0,
        "wind_kph": 0.0,
        "wind_degree": 356,
        "wind_dir": "N",
        "pressure_mb": 1013.0,
        "pressure_in": 30.4,
        "precip_mm": 0.0,
        "precip_in": 0.0,
        "humidity": 65,
        "cloud": 100,
        "feelslike_c": 2.6,
        "feelslike_f": 36.6,
        "vis_km": 16.0,
        "vis_miles": 9.0,
        "uv": 2.0,
        "gust_mph": 5.6,
        "gust_kph": 9.0
    }
}


