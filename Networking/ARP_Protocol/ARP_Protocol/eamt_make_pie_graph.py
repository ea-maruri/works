import numpy as np
import matplotlib.pyplot as plt

fig, ax = plt.subplots(figsize=(24, 12), subplot_kw=dict(aspect="equal"))

file_name = "Vendor_Percent_Results_WLAN_60.txt"
output_file_vendor_percent = open(file_name, 'r')
lines_of_txt = output_file_vendor_percent.read()
lines_of_txt = lines_of_txt.split('\n')

vendors_and_percent = []
counter = 0
percents = []
vendors = []
for line in lines_of_txt:
    parts = line.split('\t')
    percents.append(float(parts[-1]))
    vendors.append(parts[1] + ' - ' + str(round(float(parts[-1]), 1)) + '%')
    #vendors_and_percent.append(lines_of_txt[counter])
    counter = counter + 1

output_file_vendor_percent.close()


def func(pct, allvals):
    absolute = int(pct/100.*np.sum(allvals))
    return ""#"{:.1f}%\n({:d})".format(pct, absolute)


# ax[0, 0].pie(percents, labels=vendors, autopct="", shadow=True)
wedges, texts, autotexts = ax.pie(percents, autopct=lambda pct: func(pct, percents), textprops=dict(color="w"))
ax.pie(percents, labels=vendors)

#ax.legend(wedges, vendors, title="Fabricantes", loc="center right", bbox_to_anchor=(1, 0, 0.5, 1))

#plt.setp(autotexts, size=8, weight="bold")

ax.set_title("Fabricantes tarjetas de red biblioteca USFQ (WLAN-60)")

plt.savefig(file_name + ".png")

plt.show()
