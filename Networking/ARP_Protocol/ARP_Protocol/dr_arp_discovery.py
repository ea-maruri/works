#This code is based on https://null-byte.wonderhowto.com/how-to/build-arp-scanner-using-scapy-and-python-0162731/
import sys
from datetime import datetime
import time
from scapy.all import srp, Ether, ARP, conf
import urllib.request as urllib2
import json
import codecs


def get_mac_vendor(mac_address):
    """Returns the vendor of NIC given a MAC-Address (if exists)"""
    url = "http://macvendors.co/api/"  # API base url,you can also use https if you need
    request = urllib2.Request(url + mac_address, headers={'User-Agent': "API Browser"})
    response = urllib2.urlopen(request)
    # Fix: json object must be str, not 'bytes'
    reader = codecs.getreader("utf-8")
    obj = json.load(reader(response))

    return obj['result']['company']  # Return company name
    # print(obj['result']['address'])  # print company address


if __name__ == "__main__":
    ts = time.time()  # time stamp

    try:
        interface = input("Enter desired interface: ")
        ips = input("Enter range of IPs to Scan for: ")
        timeout = int(input("Enter the timeout (s): "))
    except KeyboardInterrupt:
        print("\n User requested shutdown")
        print("Quiting...")
        sys.exit(1)

    print("\n Scanning... ")
    start_time = datetime.now()

    conf.verb = 0
    ans, unans = srp(Ether(dst="ff:ff:ff:ff:ff:ff")/ARP(pdst=ips), timeout=timeout, iface=interface, verbose=False)

    output_file = open("Maruri_Muriel_LAN_" + datetime.fromtimestamp(ts).strftime('%Y.%m.%d.%H.%M.%S') + ".txt", 'w')
    output_file.write(ips + '\n')  # The network direction and mask (E.g. 192.168.0.0/24)

    mac_vendor_dict = {}
    #print ans.summary()
    print('MAC - IP')
    counter = 1
    for s, r in ans:
        mac_summary = str(r.summary()).split()
        print(mac_summary[5] + ' - ' + mac_summary[7])

        # Writing in file
        output_file.write(mac_summary[5].replace("'", "") + '\t' + mac_summary[7].replace("'", "") + '\n')
        
        print(counter, '\t', end='')
        #print(r.sprintf(r'%Ether.src% - %ARP.psrc%'))

        try:
            vendor = get_mac_vendor(mac_summary[5])
        except Exception as e:
            print("Error in 'get_mac_address' function, with id", mac_summary[5])
            vendor = "Sin fabricante"

        if vendor in mac_vendor_dict:
            mac_vendor_dict[vendor] = mac_vendor_dict[vendor] + 1
        else:
            mac_vendor_dict[vendor] = 1

        print(vendor)
        counter = counter + 1

    output_file.close()

    stop_time = datetime.now()
    total_time = stop_time - start_time

    print("\nScan Complete!")
    print("Scan Duration: %s" % total_time)

    total = sum(list(mac_vendor_dict.values()))
    print("Total:", total)

    output_file_vendor_percent = open("Vendor_Percent_Results_LAN_60.txt", 'w')
    vendor_percent = {}
    counter = 0
    for vendor in mac_vendor_dict:
        frec = mac_vendor_dict[vendor]
        vendor_percent[vendor] = (frec / total * 100)
        mac_vendor_dict[vendor] = (frec / total * 100)  # Percent of a vendor
        output_file_vendor_percent.write(str(counter) + '\t' + vendor + '\t' + str(mac_vendor_dict[vendor]) + '\n')
        counter = counter + 1

    output_file_vendor_percent.close()
    print()
    print(mac_vendor_dict)
