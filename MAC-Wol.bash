$mac = "D8:9E:F3:13:FA:79"

$macBytes = $mac.Split(':') | ForEach-Object { [Byte]('0x' + $_) }

$packet = [Byte[]](,0xFF * 6)
for ($i = 0; $i -lt 16; $i++) {
    $packet += $macBytes
}

$udpClient = New-Object System.Net.Sockets.UdpClient
$udpClient.Connect(([System.Net.IPAddress]::Broadcast),9)
$udpClient.Send($packet,$packet.Length)
$udpClient.Close()