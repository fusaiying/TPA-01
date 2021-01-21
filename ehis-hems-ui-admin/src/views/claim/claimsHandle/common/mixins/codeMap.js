export default {
  //dicts: ['apply_sourcetype', 'apply_type', 'claim_status', 'tasknode'],
  methods: {
    formatClaimStatus(row, column) {
      //return this.dict.label.claim_status[row.status]
      return this.selectDictLabel(this.claim_statusOptions, row.status)
    },
    formatApplicaReason(row, column) {
      let reason = ''
      if (row.applicationreason) {
        row.applicationreason.split(',').map((item, i) => {
          //this.dict.label.apply_type[item] ? reason += `｜${this.dict.label.apply_type[item]}` : reason = ''
          this.selectDictLabel(this.apply_typeOptions, item) ? reason += `｜${this.selectDictLabel(this.apply_typeOptions, item)}` : reason = ''
        })
      }
      return reason.slice(1)
    },
    formatConclusion(row) {
      if (this.dict.label.tasknode[row.tasknode]){
        if (row.conclusion){
          //return this.dict.label.tasknode[row.tasknode] + "-" + row.conclusion
          return this.selectDictLabel(this.tasknodeOptions, row.tasknode) + "-" + row.conclusion
        } else {
          //return this.dict.label.tasknode[row.tasknode]
          return this.selectDictLabel(this.tasknodeOptions, row.tasknode)
        }
      } else {
        return row.conclusion ? row.conclusion: ''
      }
    },
    formatLimitation(row) {
      if (row.limitation){
        return row.limitation + '天'
      } else {
        return row.limitation
      }
    }
  }
}
